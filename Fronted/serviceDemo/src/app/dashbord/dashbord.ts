import { Component, inject, OnInit } from '@angular/core';
import { DataProvider } from '../service/data-provider';

@Component({
  selector: 'app-dashbord',
  standalone: false,
  templateUrl: './dashbord.html',
  styleUrl: './dashbord.css',
})
export class Dashbord implements OnInit{
  
  data = inject(DataProvider);

  student:any;

  ngOnInit(): void {
    this.student = this.data.studentData;
  }
}
