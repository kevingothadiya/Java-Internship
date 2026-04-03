import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../service/service-provider';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { email, validate } from '@angular/forms/signals';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  standalone: false,
  templateUrl: './forgot-password.html',
  styleUrl: './forgot-password.css',
})
export class ForgotPassword {
  myService = inject(ServiceProvider)
  myRouter = inject(Router)

  myForgotForm:any

  constructor(){
    this.myForgotForm = new FormGroup({
      email:new FormControl('',[Validators.required,Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')]),
      password:new FormControl('',[Validators.required,Validators.pattern('^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$')])
    })
  }
   get formControl(){
    return this.myForgotForm.controls;
  }
  forgotPassword(){

    if (this.myForgotForm.invalid) {
      this.myForgotForm.markAllAsTouched(); // show errors
    return;
  }
    let forgotValue = this.myForgotForm.value;

    this.myService.forgotPassword(forgotValue).subscribe({
      next:(resp)=>{
        alert('Password Updated Successfully');
        console.log(resp);
        this.myRouter.navigate(['/login']);
      },
      error:(err)=>{
        console.log(err);
        alert('User Not Found')
      }
    })
  }
}
