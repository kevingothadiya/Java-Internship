import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { StorageService } from '../../service/storage-service';

@Component({
  selector: 'app-donor-history',
  standalone: false,
  templateUrl: './donor-history.html',
  styleUrl: './donor-history.css',
})
export class DonorHistory implements OnInit{

  myStorage = inject(StorageService)
  myService = inject(ServiceProvider)
  cd = inject(ChangeDetectorRef)
  
  myDonationDetails:any
  donorId = Number(this.myStorage.getItem('donorId'));

  ngOnInit(): void {
      

      if (!this.donorId) {
        console.error('User not logged in');
        return; // Stop execution if no user
      }
      this.myService.getDonationDetailsById(this.donorId).subscribe({
        next:(resp)=>{
          this.myDonationDetails = resp
          this.cd.detectChanges();
          console.log(resp);
        },
        error:(err)=>{
          console.log(err);       
        }
      })
  }
  
}
