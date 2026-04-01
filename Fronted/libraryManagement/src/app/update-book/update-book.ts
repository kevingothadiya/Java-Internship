import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../services/service-provider';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-update-book',
  standalone: false,
  templateUrl: './update-book.html',
  styleUrl: './update-book.css',
})
export class UpdateBook {
  data = inject(ServiceProvider);

  bookData:any;
  showform:boolean = false;
  bookIdInput:number | null = null;

  getBookDataById(id:number){
    this.bookIdInput = id
    this.data.getBookById(id).subscribe({
      next:(res)=>{
        this.bookData = res
        this.showform = true
      },
      error:(err)=>{
        console.log(err)
      },
      complete:()=>{
        console.log('Completed');
      }
    })
  }

  
submitUpdate(form: NgForm) {
    if (!this.bookIdInput) return;

    const updatedBook = form.value;
    updatedBook.id = this.bookIdInput; // ensure ID is sent

    this.data.updateById(updatedBook.id, updatedBook).subscribe({
      next: (resp) => {
        alert('Book updated successfully!');
        this.showform = false; // hide form after update
        this.bookData = null;
        this.bookIdInput = null;
      }
    });
  }
 
}
