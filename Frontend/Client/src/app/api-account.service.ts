import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {G_LINK} from './globals';

@Injectable({
  providedIn: 'root'
})
export class ApiAccountService {

  readonly ROOT_URL: string;


  constructor(private http : HttpClient) {

    this.ROOT_URL = 'http://' + G_LINK + ':8081/api';
   }
  get(uri : string) {
    return this.http.get(`${this.ROOT_URL}/${uri}`);
  }
  post(uri : string, payload : Object){
    return this.http.post(`${this.ROOT_URL}/${uri}`, payload);
  }
  patch(uri : string, payload : Object){
    return this.http.patch(`${this.ROOT_URL}/${uri}`, payload);
  }
  delete(uri : string){
    return this.http.delete(`${this.ROOT_URL}/${uri}`);
  }}
