import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { G_LINK } from '../globals';

@Injectable({
  providedIn: 'root'
})
export class GetCustomerIdService {
  ROOT_URL: string = 'http://' + G_LINK + ':8081/api/getMyId';
  constructor(private http : HttpClient) { }

  getId() {
    return this.http.get(this.ROOT_URL, { headers: {'Authorization': `Bearer ${sessionStorage.getItem('token')}`}});
  }

}

