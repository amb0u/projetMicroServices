import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {G_LINK} from './globals';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private baseUrl = 'http://' + G_LINK + ':8083/api';

  constructor(private http: HttpClient) { }

  getTransactions(account_number : number) : Observable<any> {
    console.log(`${this.baseUrl}/account/virements/${account_number}`);
    return this.http.get(`${this.baseUrl}/account/virements/${account_number}`);
  }
}
