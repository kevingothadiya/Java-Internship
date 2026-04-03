import { Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../service/service-provider';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-profile',
  standalone: false,
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile implements OnInit{
  
  myService = inject(ServiceProvider)
  myActivateRouter = inject(ActivatedRoute)
  myStudentData$:Observable<any> | undefined
  myId:any

  ngOnInit(): void {
    this.myActivateRouter.paramMap.subscribe((params:ParamMap)=>{
      this.myId = params.get('id')
    })
    this.myStudentData$ = this.myService.getStudentById(this.myId);
  }
}
