import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class StorageService {

  setItem(key:any,item:any){
    localStorage.setItem(key,item)
  }

  removeItem(key:any){
    localStorage.removeItem(key)
  }

  getItem(key:any){
    return localStorage.getItem(key)
  }

  removeAllItem(){
    localStorage.clear()
  }

  getUserRole(): string | null {
    const token = this.getItem('authToken');
    console.log(token);
    
    
    if (!token) return null;
 
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.roles ? payload.roles[0] : payload. Role;
  }

  isTokenExpired(token: string): boolean {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const now = Math.floor(new Date().getTime() / 1000);
      return payload.exp < now; // true if expired
    } catch (e) {
      return true; // treat invalid token as expired
    }
  }
}
