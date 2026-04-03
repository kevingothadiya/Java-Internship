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
      alert('Login Successful');
      const role = this.myStorageService.getUserRole();
          if (role === 'ADMIN') {
            this.myRouter.navigate(['/admin']);
          } else if (role === 'DONOR') {
            this.myRouter.navigate(['/donor']);
          } else if (role === 'HOSPITAL') {
            this.myRouter.navigate(['/hospital']);
          } else {
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
