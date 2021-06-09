import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InteractionService {

  client_id : any ;
  dataSource : any ;

  constructor() { }
  setDataSource(data : any){
    this.dataSource = data;
  }

  setClient_id(id : any){
    this.client_id = id;
  }

}
