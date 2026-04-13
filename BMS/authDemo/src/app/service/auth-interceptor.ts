import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { StorageService } from './storage-service';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const storage = inject(StorageService);
  const route = inject(Router);
  const token = storage.getItem('authToken');

  const publicRoutes = ['/auth/login','/auth/register','/auth/forget','/auth/forgot-password','/auth/reset-password','/auth/send-otp','/auth/verify-otp','/auth/reset-password-otp','/auth/lock-status'];

  if (publicRoutes.some((url) => req.url.includes(url))) {
    return next(req);
  }

  if (!token || storage.isTokenExpired(token)) {
    // Token expired or missing
    storage.removeAllItem();
    alert('Your Token expired ! Please Login again')
    route.navigate(['/login']);
    return throwError(() => new Error('Token expired. Logging out.'));
  }

  if (token) {
    const clonedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
    return next(clonedReq);
  }
  return next(req);
};
