import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './models/Customer';
import { G_LINK } from './globals';

@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {

  private baseUrl = "http://"+ G_LINK +":8081/api";

  constructor(private http: HttpClient) { }


  getCustomer(customer_id: number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/customers/${customer_id}`);
  }
  //gets you all the customers of the agency
  getAllCustomer(agence_id : number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/customersByAgency/${agence_id}`);
  }

  updateCustomer(customer_id: number, customer : Customer) {
    const headers = new HttpHeaders({'Content-Type':'application/json; charset=utf-8'});
    console.log(JSON.stringify(customer));
    this.http.put(`${this.baseUrl}/customers/${customer_id}`, JSON.stringify(customer) , {headers: headers}).subscribe()
  }

  deleteCustomer(customer_id: number) {
    return this.http.delete(`${this.baseUrl}/customers/${customer_id}`);
  }

  postCustomer(customer : Customer) {
    const headers = new HttpHeaders({'Content-Type':'application/json; charset=utf-8'});
    this.http.put(`${this.baseUrl}/customers/`, JSON.stringify(customer), {headers: headers});
  }

  suspend(email: any) {
    console.log(`http://${G_LINK}:8089/users/customers/deactivateByEmail/${email}`);
    return this.http.get(`http://${G_LINK}:8089/users/customers/deactivateByEmail/${email}`);
  }
}
