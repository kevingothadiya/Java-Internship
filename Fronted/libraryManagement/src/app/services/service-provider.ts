import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServiceProvider {
  http = inject(HttpClient);

  private baseUrl = "http://localhost:8080/books";


  saveBook(data:any):Observable<any>{
    return this.http.post(this.baseUrl+"/add-book",data);
  }

  getAllBooks():Observable<any>{
    return this.http.get(this.baseUrl+"/get-all-book");
  }

  deleteById(id:number):Observable<any>{
    return this.http.delete(this.baseUrl+"/delete-book-by-id/"+id)
  }

  getBookById(id:number):Observable<any>{
    return this.http.get(this.baseUrl+"/get-book-by-id/"+id);
  }

  updateById(id:number,data:any){
    return this.http.post(this.baseUrl+"/update-book/"+id,data);
  }
}
