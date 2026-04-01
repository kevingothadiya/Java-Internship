import { Component, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ServiceProvider } from '../service/service-provider';
import { email } from '@angular/forms/signals';

@Component({
  selector: 'app-register-student',
  standalone: false,
  templateUrl: './register-student.html',
  styleUrl: './register-student.css',
})
export class RegisterStudent {

  service = inject(ServiceProvider);

  mySelectedSubject:string[]=[]

  getFormData(myForm:NgForm){
    let formValues = myForm.value
    console.log(myForm.value)
    console.log(this.mySelectedSubject)
    let std = {
      name:formValues.name,
      email:formValues.email,
      gender:formValues.gender,
      phone:formValues.phone,
      city:formValues.city,
      address:formValues.address,
      subjects:this.mySelectedSubject
    }

    this.service.saveStudent(std).subscribe({
      next:(res)=>{
        console.log("success:"+res);
      },
      error:(err)=>{
        console.log(err);
      },
      complete:()=>{
        console.log("Completed");
      }
    })
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
