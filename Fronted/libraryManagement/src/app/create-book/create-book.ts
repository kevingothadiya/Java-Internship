import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../services/service-provider';

@Component({
  selector: 'app-create-book',
  standalone: false,
  templateUrl: './create-book.html',
  styleUrl: './create-book.css',
})
export class CreateBook {

  bookData = inject(ServiceProvider);

  getFormData(myForm:any){
    console.log(myForm.value)

    this.bookData.saveBook(myForm.value).subscribe({
      next:(res)=>{
        console.log('success:'+res)
        alert('Book Added Successfully')
        myForm.reset();
      },
      error:(err)=>{
        console.log(err);
      },
      complete:()=>{
        console.log("Completed");
      }
    })
  }
}
