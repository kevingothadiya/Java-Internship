import { Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../services/service-provider';

@Component({
  selector: 'app-library',
  standalone: false,
  templateUrl: './library.html',
  styleUrl: './library.css',
})
export class Library implements OnInit{
  data = inject(ServiceProvider);
  
  books:any=[];

  ngOnInit(): void {
    // this.getLibraryAllBooks();
  }

  getLibraryAllBooks(){
    this.data.getAllBooks().subscribe({
      next:(res)=>{
        this.books = res
      },
      error:(err)=>{
        console.log(err)
      },
      complete:()=>{
        console.log("Completed");
      }
    })
  }

  deleteDataById(id:number){
    this.data.deleteById(id).subscribe({
      next:(res)=>{
        console.log("Delete Successfully")
      },
      error:(err)=>{
        console.log(err);
      },
      complete:()=>{
        console.log("Completed")
      }
    })
  }
  
}
