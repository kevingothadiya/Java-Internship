import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DataProvider {
  studentData =[
    {
      fname:'Kevin',
      lname:'Gothadiya',
      age:21,
      city:'Ahmedabad',
      contactNumber:7621908832
    },
    {
      fname:'Parth',
      lname:'Vaghela',
      age:22,
      city:'Surat',
      contactNumber:9999866765
    },
    {
      fname:'Aditya',
      lname:'Talaviya',
      age:23,
      city:'Rajkot',
      contactNumber:6363442424
    },
    {
      fname:'Dinesh',
      lname:'Vasra',
      age:24,
      city:'Jamnagar',
      contactNumber:6363642426
    }
  ]
}
