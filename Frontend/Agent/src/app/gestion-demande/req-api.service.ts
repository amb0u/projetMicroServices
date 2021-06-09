import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { G_LINK } from '../globals';
@Injectable({
  providedIn: 'root'
})
export class ReqApiService {
  readonly ROOT_URL: string;
  constructor(private http : HttpClient) {  this.ROOT_URL = 'http://' + G_LINK + ':8084/api';
}


get(uri : string) {
  return this.http.get(`${this.ROOT_URL}/${uri}`, {observe: 'response'});
}
post(uri : string, payload : Object){
  return this.http.post(`${this.ROOT_URL}/${uri}`, payload, {observe: 'response'});
}

put(uri : string){
  return this.http.put(`${this.ROOT_URL}/${uri}`,  {observe: 'response'});
}

patch(uri : string, payload : Object){
  return this.http.patch(`${this.ROOT_URL}/${uri}`, payload, {observe: 'response'});
}
delete(uri : string){
  return this.http.delete(`${this.ROOT_URL}/${uri}`,{observe: 'response'});
}

traiter( dem_id : number){
  return this.put(`demandes/traite/${dem_id}`);
}

delete_demandes(dem_id : number){
  return this.delete(`demandes/${dem_id}`);
}

get_demandes(){
  let table  = [];

  return this.get(`demandes`).pipe(
    map((data ) =>{


       for (let element in data.body) {
        let value = data.body[element];
        table.push({dem_id : value.id,client_id : value.clientId, dateDemande: value.dateDemande, type: value.type, message: value.message, status: value.status });
    }

    //  data.forEach(element => {
    //    table.push({dem_id : element.id,client_id : element.clientId, dateDemande: element.dateDemande, type: element.type, message: element.message, status: element.status });
    //  });
    return table;
    }
  ))
}

}
