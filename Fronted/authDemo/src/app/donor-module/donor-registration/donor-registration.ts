import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { StorageService } from '../../service/storage-service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-donor-registration',
  standalone: false,
  templateUrl: './donor-registration.html',
  styleUrl: './donor-registration.css',
})
export class DonorRegistration {
   myService = inject(ServiceProvider);
  myStorageService = inject(StorageService);
  myRouter = inject(Router);

  donorForm: FormGroup;
  bloodGroups: string[] = ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'];

  constructor() {
    this.donorForm = new FormGroup({
      bloodGrp: new FormControl('', [Validators.required]),
      age: new FormControl('', [Validators.required, Validators.min(18), Validators.max(65)]),
      gender: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required]),
      lastDonationDate: new FormControl(''),
      available: new FormControl(true),
    });
  }

  get formControl(){
    return this.donorForm.controls;
  }

  onSubmit() {
    if (this.donorForm.invalid) return;

    const userId = this.myStorageService.getUserId();
    if (!userId) {
      alert('User not logged in');
      this.myRouter.navigate(['/login']);
      return;
    }

    const donorData = this.donorForm.value;
    donorData.users = { id: userId }; // attach logged-in user ID

    this.myService.registerDonor(donorData).subscribe({
      next: () => {
        this.myService.getDonorByUserId(userId).subscribe((res: any) => {
          localStorage.setItem('donorId', res.id);
          alert('Donor Registered Successfully!');
          this.myRouter.navigate(['/donor']);
        });
      },
      error: (err) => {
        console.log(err);
        alert('Error registering donor. Try again.');
      }
    });
  }
}
