import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ServiceProvider {
  http = inject(HttpClient);

  private url = "https://jsonplaceholder.typicode.com";

  getPostData(){
    return this.http.get(this.url+'/posts');
  }

  getPostDataById(id:number){
      return this.http.get(this.url+'/posts/'+id);
  }
}
