import { Component } from '@angular/core';

@Component({
  selector: 'app-property-binding',
  standalone: false,
  templateUrl: './property-binding.html',
  styleUrl: './property-binding.css',
})
export class PropertyBinding {

  disableData:boolean = false;

  getReadOnly(){
    if(10<20)
      return true;
    else
      return false;
  }
}
