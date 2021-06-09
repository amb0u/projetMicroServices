import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Client } from './form-client/form-client.component';
import { CurrentCompte } from './form-account/current/current.component';
import { SavingAccount } from './form-account/saving/saving.component';
import {G_LINK} from '../globals';

@Injectable({
  providedIn: 'root'
})
export class HttpReqService {

  constructor(private http: HttpClient) { }

  AddClient(client:any) {
     return this.http.post('http://' + G_LINK + ':8081/api/customers' , client) ;
  }

  addCurentAccount(curentAccount: CurrentCompte) {
     return this.http.post('http://' + G_LINK + ':8082/api/currentAccounts' , curentAccount) ;
  }
  addSavingAccount(savingAccount: SavingAccount) {
   return  this.http.post('http://' + G_LINK + ':8082/api/savingsAccounts' , savingAccount) ;
  }
}
