import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { G_LINK } from './globals';
@Injectable({
  providedIn: 'root'
})
export class SubmitdataService {
  private baseUrl = "http://"+ G_LINK +":8081/api";
  constructor( private http : HttpClient) { }

  getData(agence_id : number){
    let table  = [];
    return this.http.get(`${this.baseUrl}/customersByAgency/${agence_id}`).pipe(
      map(data =>{

      // data.forEach(element => {
      //   table.push({ prenom: element.firstName, nom: element.lastName, CIN: element.cin, email: element.email });
      // });

      // console.log(data);

      for (let element in data) {
        let value = data[element];
        table.push({id : value.id, prenom: value.firstName, nom: value.lastName, CIN: value.cin, email: value.email });
    }


      console.log(table);
      return table;
    }
    )
    );

  }

}
export interface PeriodicElement {
  nom: string;
  prenom: string;
  CIN: string;
  email: string;
}
