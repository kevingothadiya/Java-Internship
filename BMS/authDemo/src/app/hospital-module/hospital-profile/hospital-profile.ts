import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { StorageService } from '../../service/storage-service';

@Component({
  selector: 'app-hospital-profile',
  standalone: false,
  templateUrl: './hospital-profile.html',
  styleUrl: './hospital-profile.css',
})
export class HospitalProfile implements OnInit{
  
  myService = inject(ServiceProvider);
  myStorage = inject(StorageService);
  cd = inject(ChangeDetectorRef)
  
  hospitalDetails:any;
  
  ngOnInit(): void {

    const userId = this.myStorage.getUserId();

    if (!userId) {
      console.error('User not logged in');
      return;
    }

    this.myService.getHospitalByUserId(userId).subscribe({
      next:(res)=>{
        this.hospitalDetails = res
        this.cd.detectChanges();
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }


}
