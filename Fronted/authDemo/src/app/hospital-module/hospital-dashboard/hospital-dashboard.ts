import { Component, inject } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { StorageService } from '../../service/storage-service';

@Component({
  selector: 'app-hospital-dashboard',
  standalone: false,
  templateUrl: './hospital-dashboard.html',
  styleUrl: './hospital-dashboard.css',
})
export class HospitalDashboard {
  isHomeVisible = true;

  constructor(private router: Router, private storage: StorageService) {

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isHomeVisible = (event.urlAfterRedirects === '/hospital');
      }
    });
  }

  onActivate(event: any) {
    this.isHomeVisible = false;
  }

}
