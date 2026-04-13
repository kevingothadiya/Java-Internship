import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { StorageService } from '../../service/storage-service';

@Component({
  selector: 'app-blood-request-history',
  standalone: false,
  templateUrl: './blood-request-history.html',
  styleUrl: './blood-request-history.css',
})
export class BloodRequestHistory implements OnInit{
  myService = inject(ServiceProvider);
  myStorage = inject(StorageService);
  cd = inject(ChangeDetectorRef)

  hospitalId: number = 0;
  
  hospitalBloodReqHistory:any
  
  ngOnInit(): void {
    const id = this.myStorage.getItem('hospitalId');

  if (id) {
    this.hospitalId = Number(id);

    this.myService.getBloodRequestHistory(this.hospitalId).subscribe({
      next: (resp) => {
        this.hospitalBloodReqHistory = resp;
        this.cd.detectChanges(); // optional
      },
      error: (err) => {
        console.log(err);
      }
    });
  } else {
    console.error('Hospital ID not found in storage');
  }
  }
}
