import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { form, submit } from '@angular/forms/signals';
import { Subject } from 'rxjs';
import { ServiceProvider } from '../service/service-provider';

@Component({
  selector: 'app-register-student',
  standalone: false,
  templateUrl: './register-student.html',
  styleUrl: './register-student.css',
})
export class RegisterStudent {

  service = inject(ServiceProvider)

  public myForm:any;

  mySelectedSubject:string[]=[]

  constructor(){
    this.myForm = new FormGroup({
      name:new FormControl('',[Validators.required]),
      email:new FormControl('',[Validators.required,Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')]),
      gender:new FormControl(''),
      phone:new FormControl('',[Validators.required,Validators.pattern('^[0-9]{10}$')]),
      city:new FormControl('',[Validators.required]),
      address:new FormControl('',[Validators.required]),
      language:new FormControl('',[Validators.required]),
      subjects:new FormControl('')
    })
  }

  savedata(myForm:NgForm){
    console.log(this.myForm.value);

    let formValue = myForm.value;
    let std = {
      name:formValue.name,
      email:formValue.email,
      gender:formValue.gender,
      phone:formValue.phone,
      city:formValue.city,
      address:formValue.address,
      subjects:this.mySelectedSubject
    }

    this.service.saveStudent(std).subscribe({
      next:(res)=>{
        console.log("success:"+res)
      },
      error:(err)=>{
        console.log(err);
      },
      complete:()=>{
        console.log("Completed");
        
      }
    })

    console.log(std)
  }

  get formControl(){
    return this.myForm.controls;
  }

  changeSubject(ev:any){
    if(ev.target.checked){
      this.mySelectedSubject.push(ev.target.value)
    }
    else{
      let index = this.mySelectedSubject.indexOf(ev.target.value)
      this.mySelectedSubject.splice(index,1)
    }
  }
}
