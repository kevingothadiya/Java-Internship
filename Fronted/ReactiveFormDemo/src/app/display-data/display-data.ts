import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../service/service-provider';

@Component({
  selector: 'app-display-data',
  standalone: false,
  templateUrl: './display-data.html',
  styleUrl: './display-data.css',
})
export class DisplayData {

  service = inject(ServiceProvider)

  displayTable = false;

  myStudentData:any

  getAllStudent(){
    this.service.getStudent().subscribe({
      next:(res)=>{
        this.displayTable = true
        this.myStudentData = res
      },
      error:(err)=>{
        console.log(err);
      },
      complete:()=>{
        console.log("Completed");
      }
    })
  }

  deleteStudentById(id:number){
    this.service.deleteStudent(id).subscribe({
       next:(res)=>{
        console.log("Delete Successfully:"+res);
        
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
