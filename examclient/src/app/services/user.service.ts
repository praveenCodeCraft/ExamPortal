import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import baseUrl from './helper';
import { environment } from 'src/enviornment/enviornment';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  url = environment.baseUrl;
  //add users
  public addUsers(user: any){
      return this.http.post(this.url + '/user/' , user);

      };
  }

