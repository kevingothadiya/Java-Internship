import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from './storage-service';

@Injectable({
  providedIn: 'root',
})
export class ServiceProvider {
  http = inject(HttpClient)
  myStorageService = inject(StorageService)

  baseUrl = 'http://localhost:8080'

  saveUser(data:any):Observable<any>{
    return this.http.post(this.baseUrl+"/auth/register",data,{responseType:'text'});
  }

  loginUser(data:any):Observable<any>{
    return this.http.post(this.baseUrl+"/auth/login",data);
  }

  isLoggedIn(){
    let token = this.myStorageService.getItem('authToken');
    if(token)
      return true
    return false
  }

  getUser():Observable<any>{
    return this.http.get(this.baseUrl+"/admin/user");
  }

  forgotPassword(data:any):Observable<any>{
    return this.http.post(this.baseUrl+"/auth/forget",data,{responseType:'text'});
  }

  deleteUserById(id:number):Observable<any>{
    return this.http.delete(this.baseUrl+"/admin/delete/"+id,{responseType:'text'});
  }

  updateUser(id: number,data:any): Observable<any> {
    return this.http.put(this.baseUrl + '/admin/update/' +id, data,{responseType:'text'});
  }

  downloadUserExcel(): Observable<Blob> {
    return this.http.get(this.baseUrl+"/admin/excel/user/download", { responseType: 'blob' });
  }

  getDonationDetails():Observable<any>{
    return this.http.get(this.baseUrl+"/admin/donation")
  }

  approveDonationRequest(id:number):Observable<any>{
    return this.http.put(this.baseUrl+"/admin/donor/"+id+"/approve",{responseType:'text'});
  }
}
