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

  loginUser(data: any): Observable<any> {
    return this.http.post(this.baseUrl + "/auth/login", data, { responseType: 'text' });
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

  getUsersPaged(page: number, size: number): Observable<any> {
    return this.http.get(this.baseUrl + `/admin/user/paged?page=${page}&size=${size}`);
  }

  forgotPassword(data:any):Observable<any>{
    return this.http.post(this.baseUrl+"/auth/forget",data,{responseType:'text'});
  }

  sendResetLink(email: string): Observable<any> {
    return this.http.post(this.baseUrl + "/auth/forgot-password", { email }, { responseType: 'text' });
  }

  resetPassword(token: string, newPassword: string): Observable<any> {
    return this.http.post(this.baseUrl + "/auth/reset-password", { token, newPassword }, { responseType: 'text' });
  }

  sendOtp(email: string): Observable<any> {
    return this.http.post(this.baseUrl + "/auth/send-otp", { email }, { responseType: 'text' });
  }

  getLockStatus(email: string): Observable<any> {
    return this.http.get(this.baseUrl + "/auth/lock-status", { params: { email } });
  }

  verifyOtp(email: string, otp: string): Observable<any> {
    return this.http.post(this.baseUrl + "/auth/verify-otp", { email, otp }, { responseType: 'text' });
  }

  resetPasswordWithOtp(email: string, otp: string, newPassword: string): Observable<any> {
    return this.http.post(this.baseUrl + "/auth/reset-password-otp", { email, otp, newPassword }, { responseType: 'text' });
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

  downloadHospitalExcel(): Observable<Blob> {
    return this.http.get(this.baseUrl+"/admin/excel/hospital/download", { responseType: 'blob' });
  }

  downloadBloodStockExcel(): Observable<Blob> {
    return this.http.get(this.baseUrl+"/admin/excel/blood-stoke/download", { responseType: 'blob' });
  }

  downloadDonorDetailsExcel(): Observable<Blob> {
    return this.http.get(this.baseUrl+"/admin/excel/donor-details/download", { responseType: 'blob' });
  }

  getDonationDetails():Observable<any>{
    return this.http.get(this.baseUrl+"/admin/donation")
  }

  approveDonationRequest(id:number):Observable<any>{
    return this.http.put(this.baseUrl+"/admin/donor/"+id+"/approve",{responseType:'text'});
  }

  rejectDonationRequest(id:number):Observable<any>{
    return this.http.put(this.baseUrl+"/admin/donor/"+id+"/reject",{},{responseType:'text'});
  }

  getDonationsPaged(page:number, size:number):Observable<any>{
    return this.http.get(this.baseUrl+`/admin/donation/paged?page=${page}&size=${size}`);
  }

  approveBloodRequest(id:number):Observable<any>{
    return this.http.put(this.baseUrl+"/admin/blood/request/"+id+"/approve",{},{responseType:'text'});
  }

  rejectBloodRequest(id:number):Observable<any>{
    return this.http.put(this.baseUrl+"/admin/blood/request/"+id+"/reject",{},{responseType:'text'});
  }

  getBloodRequestsPaged(page:number, size:number):Observable<any>{
    return this.http.get(this.baseUrl+`/admin/blood/request/paged?page=${page}&size=${size}`);
  }

  getDonorByUserId(userId:number):Observable<any>{
    return this.http.get(this.baseUrl+"/donor/profile/user/"+userId);
  }

  registerDonor(donorData: any):Observable<any> {
    return this.http.put(this.baseUrl+'/donor/profile', donorData,{responseType:'text'});
  }

  getDonationDetailsById(userId:number):Observable<any>{
    return this.http.get(this.baseUrl+'/donor/history/'+userId);
  }

  getDonorProfileByUserId(userId:number):Observable<any>{
    return this.http.get(this.baseUrl+'/donor/profile/user/'+userId)
  }

  donateBlood(data:any):Observable<any>{
    return this.http.post(this.baseUrl+'/donor/donate',data,{responseType:'text'})
  }

  getHospitalByUserId(userId:any):Observable<any>{
    return this.http.get(this.baseUrl+'/hospital/profile/'+userId);
  }

  registerHospital(data:any):Observable<any>{
    return this.http.post(this.baseUrl+'/hospital/add',data,{responseType:'text'})
  }

  getBloodRequestHistory(hospitalId:number):Observable<any>{
    return this.http.get(this.baseUrl+'/hospital/request/history/'+hospitalId);
  }

  generateRequestForBlood(data:any):Observable<any>{
    return this.http.post(this.baseUrl+'/hospital/request',data,{responseType:'text'})
  }
}
