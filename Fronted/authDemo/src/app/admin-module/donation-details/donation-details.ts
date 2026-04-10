import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';

@Component({
  selector: 'app-donation-details',
  standalone: false,
  templateUrl: './donation-details.html',
  styleUrl: './donation-details.css',
})
export class DonationDetails implements OnInit{

  myDonationDetails:any
  myService = inject(ServiceProvider)
  cd = inject(ChangeDetectorRef)

  ngOnInit(): void {
    this.myService.getDonationDetails().subscribe({
      next:(resp)=>{
        this.myDonationDetails = resp
        this.cd.detectChanges();
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  approveDonationReq(id:number){
    this.myService.approveDonationRequest(id).subscribe({
      next:(resp)=>{
        alert('Donation Request Approved');
        this.cd.detectChanges();
        console.log(resp);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

}
