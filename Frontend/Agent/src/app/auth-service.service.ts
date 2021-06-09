import { Injectable } from '@angular/core';
import { HttpLoginService } from './http-login.service';




@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {


  constructor(private webReqService : HttpLoginService) { }

  login(objet : object){
    return this.webReqService.post('login', objet);
  }
  loggedIn(){
    return !!sessionStorage.getItem('token');
  }
  getToken(){
    return sessionStorage.getItem('token');
  }
  logout(){
    sessionStorage.removeItem('token');
  }
  passChange(objet : object){
    return this.webReqService.put('users/customers',objet);
  }


}
