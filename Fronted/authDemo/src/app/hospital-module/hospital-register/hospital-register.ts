import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { StorageService } from '../../service/storage-service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-hospital-register',
  standalone: false,
  templateUrl: './hospital-register.html',
  styleUrl: './hospital-register.css',
})
export class HospitalRegister {
  myService = inject(ServiceProvider);
  myStorageService = inject(StorageService);
  myRouter = inject(Router);

  hospitalForm: FormGroup;

  constructor() {
    this.hospitalForm = new FormGroup({
      hospitalName: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      contactNum: new FormControl('', [
        Validators.required,
        Validators.pattern('^[0-9]{10}$')
      ]),
      licenceNumber: new FormControl('', [Validators.required]),
    });
  }

  get formControl() {
    return this.hospitalForm.controls;
  }

  onSubmit() {
    if (this.hospitalForm.invalid) return;

    const userId = this.myStorageService.getUserId();

    if (!userId) {
      alert('User not logged in');
      this.myRouter.navigate(['/login']);
      return;
    }

    const hospitalData = this.hospitalForm.value;
    hospitalData.users = { id: userId }; // attach userId

    this.myService.registerHospital(hospitalData).subscribe({
      next: () => {

        this.myService.getHospitalByUserId(userId).subscribe((res: any) => {

          localStorage.setItem('hospitalId', res.id);

          alert('Hospital Registered Successfully!');
          this.myRouter.navigate(['/hospital']);
        });

      },
      error: (err) => {
        console.log(err);
        alert('Error registering hospital. Try again.');
      }
    });
  }
}
