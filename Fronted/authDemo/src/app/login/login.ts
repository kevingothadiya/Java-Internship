import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ServiceProvider } from '../service/service-provider';
import { Router } from '@angular/router';
import { StorageService } from '../service/storage-service';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  myService = inject(ServiceProvider)
  myStorageService = inject(StorageService)
  myRouter = inject(Router)

  public myLogin:any;

  constructor(){
    this.myLogin = new FormGroup({
      email:new FormControl('',[Validators.required,Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')]),
      password:new FormControl('',[Validators.required]),
    })
  }
  get formControl(){
    return this.myLogin.controls;
  }

  onVarify(myLogin:NgForm){
    let loginValue = myLogin.value;
    let std = {
      email:loginValue.email,
      password:loginValue.password,
    }
    this.myService.loginUser(std).subscribe({
      next:(res)=>{
        
      this.myStorageService.setItem('authToken',res.token)
      this.myStorageService.setItem('role',res.role)
      this.myStorageService.setItem('userId', res.id);

      alert('Login Successful');

      const userId = this.myStorageService.getUserId();
      const role = this.myStorageService.getUserRole();

          if (role === 'ADMIN') 
          {
            this.myRouter.navigate(['/admin']);
          } 
          else if (role === 'DONOR') 
          {
            if (userId !== null) {
            this.myService.getDonorByUserId(userId).subscribe({
              next: (res) => {
                localStorage.setItem('donorId',res.id)
                this.myRouter.navigate(['/donor']);
              },
              error: (err) => {
                if (err.status === 404) {
                  this.myRouter.navigate(['/donor-registration']);
                }
                else {
                  alert('Something went wrong while checking donor profile');
                }
              }
            });
          } 
          else {
            alert('User ID not found. Please login again.');
            this.myRouter.navigate(['/login']);
          }
          } 
          else if (role === 'HOSPITAL') 
          {
            if(userId !== null){
              this.myService.getHospitalByUserId(userId).subscribe({
                next:(res)=>{
                  localStorage.setItem('hospitalId',res.id);
                  this.myRouter.navigate(['/hospital']);
                },
                error:(err)=>{
                  if(err.status===404){
                    this.myRouter.navigate(['/hospital-register'])
                  }
                  else {
                    alert('Something went wrong while checking hospital profile');
                  }
                }
              });
            }
            else {
              alert('User ID not found. Please login again.');
              this.myRouter.navigate(['/login']);
            }
          } 
          else {
            this.myRouter.navigate(['/user']);
          }
      
      },
      error:(err)=>{
        console.log(err);
      if (err.status === 401 || err.status === 400 || err.status === 500) {
        alert('Invalid Email or Password!');
      } else {
        alert('Something went wrong. Please try again.');
      }
      }
    })
  }
}
