import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {G_LINK} from './globals';

@Injectable({
  providedIn: 'root'
})
export class AccountsServiceService {

  private baseUrl = 'http://' + G_LINK + ':8082/api';

  constructor(private http: HttpClient) { }


  //TODO(): To be chanegd later for retreival using client_id "customer/savingsAccounts"
  getAccount(client_id : number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/currentAccounts/${client_id}`);
  }

  getSavingsAccount(client_id : number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/savingsAccounts/${client_id}`);
  }

}
