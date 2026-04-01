import { Component } from '@angular/core';

@Component({
  selector: 'app-for-directives',
  standalone: false,
  templateUrl: './for-directives.html',
  styleUrl: './for-directives.css',
})
export class ForDirectives {
  stateList=["GJ","MH","DL","MP","AP","TN"]

  studentList=[
    {
      fname:'Parth',
      lname:'Vaghela',
      age:21,
      city:'Ahmedabad'
    },
    {
      fname:'Aditya',
      lname:'Talaviya',
      age:23,
      city:'Surat'
    },
    {
      fname:'Kevin',
      lname:'Gothadiya',
      age:30,
      city:'Baroda'
    },
    {
      fname:'Dinesh',
      lname:'Vasra',
      age:25,
      city:'Jamnagar'
    }
  ]
}
