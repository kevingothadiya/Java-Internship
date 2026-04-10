import { Component, inject } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { StorageService } from '../../service/storage-service';

@Component({
  selector: 'app-donor-dashboard',
  standalone: false,
  templateUrl: './donor-dashboard.html',
  styleUrl: './donor-dashboard.css',
})
export class DonorDashboard {
isHomeVisible = true;

  constructor(private router: Router, private storage: StorageService) {
    // Hide home content when a child route is active
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isHomeVisible = (event.urlAfterRedirects === '/donor');
      }
    });
  }


  // Optional: hide home content when any route is activated via router-outlet
  onActivate(event: any) {
    this.isHomeVisible = false;
  }
}
