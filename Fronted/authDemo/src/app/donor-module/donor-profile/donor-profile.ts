import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { StorageService } from '../../service/storage-service';

@Component({
  selector: 'app-donor-profile',
  standalone: false,
  templateUrl: './donor-profile.html',
  styleUrl: './donor-profile.css',
})
export class DonorProfile {
  myService = inject(ServiceProvider);
  myStorage = inject(StorageService);
  cd=inject(ChangeDetectorRef);

  donorDetails:any

  ngOnInit() {
    const userId = this.myStorage.getUserId();

    if (!userId) {
    console.error('User not logged in');
    return; // Stop execution if no user
  }

    this.myService.getDonorProfileByUserId(userId).subscribe({
      next: (res) => {
        this.donorDetails = res;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.log(err);
        
      }
    });
  }
}
