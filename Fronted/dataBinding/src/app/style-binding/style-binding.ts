import { Component } from '@angular/core';

@Component({
  selector: 'app-style-binding',
  standalone: false,
  templateUrl: './style-binding.html',
  styleUrl: './style-binding.css',
})
export class StyleBinding {
  myBgColor = 'background-color : red;'

  myStyle = {
    'background-color': 'rgb(152, 184, 185)',
    'border': '2px solid black',
    'fontSize' : '30px',
    'font': 'bold'
  }
}
