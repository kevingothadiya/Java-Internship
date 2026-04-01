import { Component } from '@angular/core';

@Component({
  selector: 'app-interpolition-demo',
  standalone: false,
  templateUrl: './interpolition-demo.html',
  styleUrl: './interpolition-demo.css',
})
export class InterpolitionDemo {

  public name:string = 'Kevin'

  public mobileNumber:number = 7621908832

  public firstNum:number = 10;

  public secondNum:number = 20;

  multiplication():number{
    return this.firstNum * this.secondNum;
  }

  studentData():any{
    let student={
      name:this.name,
      age:21,
      branch:'B-tech IT',
      clg:"Gandhinagar University"
    }
    return student;
  }

  
}
