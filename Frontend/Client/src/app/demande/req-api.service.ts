import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReqApiService {
  readonly ROOT_URL: string;
  constructor(private http : HttpClient) {  this.ROOT_URL = 'http://localhost:8084/api';
}


get(uri : string) {
  return this.http.get(`${this.ROOT_URL}/${uri}`, {observe: 'response'});
}
post(uri : string, payload : Object){
  return this.http.post(`${this.ROOT_URL}/${uri}`, payload, {observe: 'response'});
}

put(uri : string, payload : Object){
  return this.http.put(`${this.ROOT_URL}/${uri}`, payload, {observe: 'response'});
}

patch(uri : string, payload : Object){
  return this.http.patch(`${this.ROOT_URL}/${uri}`, payload, {observe: 'response'});
}
delete(uri : string){
  return this.http.delete(`${this.ROOT_URL}/${uri}`,{observe: 'response'});
}


faire_demande(objet : object){
  return this.post('demandes',objet);
}
get_demande(client_id : number){
  return this.get(`customer/demandes/${client_id}`);
}

}
