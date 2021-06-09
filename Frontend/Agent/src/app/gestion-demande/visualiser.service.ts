import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VisualiserService {

  client_id : number ;
  dem_id : number;
  type : string = "";
  message : string = "";
  date : string = "";

  constructor() { }

  setVariables(client_id, dem_id, type, message, date){
    this.client_id = client_id;
    this.dem_id = dem_id;
    this.type = type;
    this.message = message;
    this.date = date;
  }
}
