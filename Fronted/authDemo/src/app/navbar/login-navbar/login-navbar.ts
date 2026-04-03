import { Component, inject } from '@angular/core';
import { StorageService } from '../../service/storage-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-navbar',
  standalone: false,
  templateUrl: './login-navbar.html',
  styleUrl: './login-navbar.css',
})
export class LoginNavbar {
  storage = inject(StorageService);
  router = inject(Router);

  isLoggedIn(): boolean {
    return !!this.storage.getItem('authToken');
  }

  logout() {
    this.storage.removeAllItem(); 
    alert('Logged out successfully');
    this.router.navigate(['/login']);
  }
}
