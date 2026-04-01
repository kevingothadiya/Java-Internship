import { Component } from '@angular/core';

@Component({
  selector: 'app-if-directive',
  standalone: false,
  templateUrl: './if-directive.html',
  styleUrl: './if-directive.css',
})
export class IfDirective {
  isDisplay = true;

  fullname:string=''
}
