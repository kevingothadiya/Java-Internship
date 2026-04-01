import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../service/service-provider';
import { Observable } from 'rxjs';
import { NgForm } from '@angular/forms';
import { email } from '@angular/forms/signals';

@Component({
  selector: 'app-operation-at-student',
  standalone: false,
  templateUrl: './operation-at-student.html',
  styleUrl: './operation-at-student.css',
})
export class OperationAtStudent {
  
  service = inject(ServiceProvider);

  myStudentData:any=[]

  getAllStudent(){
    this.service.getStudent().subscribe({
      next:(res)=>{
        this.myStudentData = res
      },
      error:(err)=>{
        console.log(err);
      },
      complete:()=>{
        console.log("Completed")
      }
    })
  }

  selectedId:number | null= null;

  setDeletId(id:number){
    this.selectedId = id
  }

  confirmDelete(){
    if(this.selectedId != null){
      this.deleteById(this.selectedId);
      this.selectedId = null;
    }

    // Close modal manually
    const modal = document.getElementById('deleteModal');
    if (modal) {
      const modalInstance = (window as any).bootstrap.Modal.getInstance(modal);
      modalInstance.hide();
    }
  }

  deleteById(id:number){
    this.service.deleteStudentByID(id).subscribe({
      next:(res)=>{
        console.log("Delete Successfully");
      },
      error:(err)=>{
        console.log(err);
      },
      complete:()=>{
        console.log("completed");
      }
    })
  }

  myStudentDataById:any ={};

  getStudentData(id:number){
    this.service.getStudentByID(id).subscribe({
      next:(res)=>{
        this.myStudentDataById = res;
        this.showform = true;
        this.StudentIdInput = res.id;
      },
      error:(err)=>{
        console.log(err);
      },
      complete:()=>{
        console.log("completed");
      }
    })
  }

   mySelectedSubject:string[]=[]

  changeSubject(ev: any) {
  if (ev.target.checked) {
    this.mySelectedSubject.push(ev.target.value);
  } else {
    const index = this.mySelectedSubject.indexOf(ev.target.value);
    if (index > -1) {
      this.mySelectedSubject.splice(index, 1);
    }
  }
  this.myStudentDataById.subjects = this.mySelectedSubject;
}

  StudentData:any;
  showform:boolean = false;
  StudentIdInput:number | null = null;

  submitUpdate(form:NgForm){
    if(!this.StudentIdInput) return;

    let updateStudent = form.value;

    let std ={
      name:updateStudent.name,
      email:updateStudent.email,
      gender:updateStudent.gender,
      phone:updateStudent.phone,
      city:updateStudent.city,
      address:updateStudent.address,
      subjects:updateStudent.subjects
    }

    this.service.updateStudent(this.StudentIdInput,std).subscribe({
      next:(res:any)=>{
        this.mySelectedSubject = res.subjects || []; 
        console.log("success : "+res);
      },
      error:(err)=>{
        console.log(err);
      },
      complete:()=>{
        console.log("Completed");
      }
    })
  }
}
