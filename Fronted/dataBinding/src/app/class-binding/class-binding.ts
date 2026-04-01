import { Component } from '@angular/core';

@Component({
  selector: 'app-class-binding',
  standalone: false,
  templateUrl: './class-binding.html',
  styleUrl: './class-binding.css',
})
export class ClassBinding {
  myStyle = 'myFontColor myFontBgColor'
  myFirstStyle = 'myFontColor'
  mySecondStyle = 'myFontType'
  condition = false;
  styleSheet = new myStyleSheet();
}

class myStyleSheet{
  myFontColor=true;
  myFontType=false;
  myFontBgColor=true;
}