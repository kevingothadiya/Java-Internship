import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-donor-dashboard',
  standalone: false,
  templateUrl: './donor-dashboard.html',
  styleUrl: './donor-dashboard.css',
})
export class DonorDashboard {
  isHomeVisible = true;

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isHomeVisible = (event.urlAfterRedirects === '/donor');
      }
    });
  }

  onActivate(event: any) {
    this.isHomeVisible = false;
  }
}
