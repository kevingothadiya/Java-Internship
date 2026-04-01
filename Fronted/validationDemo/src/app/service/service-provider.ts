import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServiceProvider {
  http = inject(HttpClient);

  private baseUrl = "http://localhost:8080/student"

  saveStudent(data:any):Observable<any>{
    return this.http.post(this.baseUrl+"/add",data);
  }

  getStudent():Observable<any>{
    return this.http.get(this.baseUrl+"/get");
  }
  
  deleteStudentByID(id:number):Observable<any>{
    return this.http.delete(this.baseUrl+"/delete/"+id);
  }

  getStudentByID(id:number):Observable<any>{
    return this.http.get(this.baseUrl+"/get/"+id);
  }

  updateStudent(id:number,data:any){   
    return this.http.post(this.baseUrl+"/update/"+id,data);
  }
}
