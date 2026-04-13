import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { StorageService } from '../service/storage-service';

export const authGuard: CanActivateFn = (route, state) => {
    const myRouter = inject(Router);
    const myStorageService = inject(StorageService);
    // const token = myStorageService.getItem('authToken')
    const role = myStorageService.getUserRole();

  if (role==='ADMIN' || role==='DONOR' || role==='HOSPITAL') {
    return true;
  } 
  else {
    myRouter.navigate(['/login']);
    return false;
  }
};
