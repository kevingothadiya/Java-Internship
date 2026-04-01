import { Component } from '@angular/core';

@Component({
  selector: 'app-pipe-demo',
  standalone: false,
  templateUrl: './pipe-demo.html',
  styleUrl: './pipe-demo.css',
})
export class PipeDemo {
  name:string='Kevin Gothadiya'

  studentData ={
    fname:"kevin",
    lname:"Gothadiya",
    age:21,
    address:"Ahmedabad"
  }

  date:Date  = new Date();

  myMoney:number = 250;

  myNumber1:number = 0.20;
  myNumber2:number = 15.3579;
  myNumber3:number = 5;

  myHeaderStyle = {
    'text-align': 'center',
    'background-color': 'rgb(48, 108, 110)', 
    'color': 'rgb(224, 224, 194)'
  }
  
}
