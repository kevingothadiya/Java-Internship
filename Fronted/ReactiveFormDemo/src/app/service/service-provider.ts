import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServiceProvider {
  http = inject(HttpClient);

  baseUrl = 'http://localhost:8080/student';

  saveStudent(data:any):Observable<any>{
    return this.http.post(this.baseUrl+"/add",data);
  }

  getStudent():Observable<any>{
    return this.http.get(this.baseUrl+"/get");
  }

  deleteStudent(id:number):Observable<any>{
    return this.http.delete(this.baseUrl+"/delete/"+id);
  }
}
