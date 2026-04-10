import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { StorageService } from '../../service/storage-service';

@Component({
  selector: 'app-donation-request',
  standalone: false,
  templateUrl: './donation-request.html',
  styleUrl: './donation-request.css',
})
export class DonationRequest {


  quantity: number = 0;
  
  myService = inject(ServiceProvider)
  myStorage = inject(StorageService)
  donorId = this.myStorage.getItem('donorId');

  donate() {
    const payload = {
      quantity: this.quantity,
      donorDetails: {
        id: this.donorId
      }
    };

    this.myService.donateBlood(payload).subscribe({
      next: (res) => {
        console.log(res);
        alert('Donation Request Send Successfully');
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}
