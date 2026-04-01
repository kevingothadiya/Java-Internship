import { Component } from '@angular/core';

@Component({
  selector: 'app-event-binding',
  standalone: false,
  templateUrl: './event-binding.html',
  styleUrl: './event-binding.css',
})
export class EventBinding {
  public name:string='';
  public userPass:string='';
  
  setName(fname:string,pass:string){
    this.name = fname;
    this.userPass = pass;
  }


  textColor: string = 'black';
  onSave(){
    alert("You Click on Button");
  }
  redColor(){
    this.textColor = 'red';
  }
  yellowColor(){
    this.textColor = 'yellow';
  }
  blueColor(){
    this.textColor = 'blue';
  }

  incdec:number = 0;

  incrementDecrement(flag:string){
    if(flag==='add'){
      this.incdec++;
    }
    else{
      if(this.incdec!=0){
        this.incdec--;
      }
    }
  }
}
