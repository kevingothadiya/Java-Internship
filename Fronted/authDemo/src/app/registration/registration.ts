import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../service/service-provider';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  standalone: false,
  templateUrl: './registration.html',
  styleUrl: './registration.css',
})
export class Registration {

  myService = inject(ServiceProvider);
  myRoute = inject(Router);

  public myForm:any;

   constructor(){
    this.myForm = new FormGroup({
      name:new FormControl('',[Validators.required]),
      email:new FormControl('',[Validators.required,Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')]),
      password:new FormControl('',[Validators.required,Validators.pattern('^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$')]),
      phoneNum:new FormControl('',[Validators.required,Validators.pattern('^[0-9]{10}$')]),
      role:new FormControl(''),
      status:new FormControl(''),
    })
  }

  get formControl(){
    return this.myForm.controls;
  }
  onSubmit(myForm:NgForm) {
    
    let formValue = myForm.value;
    let std = {
      name:formValue.name,
      email:formValue.email,
      password:formValue.password,
      phoneNum:formValue.phoneNum,
      role:formValue.role,
      status:'ACTIVE'
    }
    this.myService.saveUser(std).subscribe({
        next: (res) => {
          console.log(res);
          
          alert('User Registered Successfully');
          this.myRoute.navigate(['/login']);
        },
        error: (err) => {
          console.error(err);
        }
      });
  }
}
