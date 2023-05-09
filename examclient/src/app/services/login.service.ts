import { HttpClient } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { environment } from 'src/enviornment/enviornment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  url = environment.baseUrl;

  constructor(private http: HttpClient) {}

  loginUser(loginData: any) {
    debugger;
    return this.http.post(this.url + '/user/login', loginData);
  }

  //set token in local storage
  public setToken(token: any) {
    localStorage.setItem('token', token);
    return true;
  }

  //get token from the local storage
  public getToken() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == null || tokenStr == '' || tokenStr == undefined) {
      return tokenStr;
    } else {
      return false;
    }
  }
  //remove token from local storage
  public logout() {
    localStorage.removeItem('token');
    return true;
  }

  //set user details at localStorage
  public setUser(user :any ){
    localStorage.setItem('user' , JSON.stringify(user));
  }

  //get user details from localStorage
  public getUser(){
    let userStr = localStorage.getItem('user');
    if(userStr != null){
      return JSON.parse(userStr);
    }else{
      this.logout();
      return null ;
    }
  }

  public userRole(){
    let userRole = this.getUser();
    return userRole.authorities[0].authority ;
  }
}
