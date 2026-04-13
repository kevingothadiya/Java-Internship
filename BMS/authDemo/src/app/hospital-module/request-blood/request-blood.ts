import { Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { StorageService } from '../../service/storage-service';

@Component({
  selector: 'app-request-blood',
  standalone: false,
  templateUrl: './request-blood.html',
  styleUrl: './request-blood.css',
})
export class RequestBlood implements OnInit{
  myService = inject(ServiceProvider);
  myStorage = inject(StorageService);

  bloodGroups: string[] = ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'];

  request = {
    bloodGrp: '',
    quantity: 0,
    hospital: {
      id: 0
    }
  };

  ngOnInit(): void {
    const hospitalId = this.myStorage.getItem('hospitalId');

    if (hospitalId) {
      this.request.hospital.id = Number(hospitalId);
    } else {
      alert('Hospital not found. Please login again.');
    }
  }

  submitRequest(form: any) {
    const confirmAction = confirm(
      `Blood Group: ${this.request.bloodGrp}\nQuantity: ${this.request.quantity} Units\n\nDo you want to proceed?`
    );
    if (!confirmAction) {
      return;
    }

    this.myService.generateRequestForBlood(this.request).subscribe({
      next: (res) => {
        alert('Blood request sent successfully!');
        form.resetForm()
        this.request.hospital.id = Number(this.myStorage.getItem('hospitalId'));
      },
      error: (err) => {
        console.log(err);

        try {
          alert(err.error.message || JSON.parse(err.error).message);
        } catch {
          alert(err.error || 'Something went Wrong');
        }
      }
    });
  }
}
