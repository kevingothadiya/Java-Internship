import { Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../services/service-provider';

@Component({
  selector: 'app-display-data',
  standalone: false,
  templateUrl: './display-data.html',
  styleUrl: './display-data.css',
})
export class DisplayData implements OnInit{
  myService = inject(ServiceProvider);

  myPostData:any = [];
  myPostDataById:any=[];
  resetFlag = false;

  ngOnInit(): void {
    // this.getAllPostData(); 
    // this.getPostById(id);
  }

  getAllPostData(){
    this.resetFlag=false;
    this.myService.getPostData().subscribe((resp:any)=>{
      this.myPostData = resp;
    })
  }

  getPostById(id:number){
    this.resetFlag=false;
      this.myService.getPostDataById(id).subscribe((resp:any)=>{
        this.myPostDataById = resp;
      })
  }

  onReset(){
    this.resetFlag=true;
  }

}
