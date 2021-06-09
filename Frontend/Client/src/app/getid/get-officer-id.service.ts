import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { G_LINK } from '../globals';


@Injectable({
  providedIn: 'root'
})
export class GetOfficerIdService {

  ROOT_URL : string = 'http://' + G_LINK + ':8085/api/getMyId';
  constructor(private http : HttpClient) { }

  getId() {
    this.http.get(this.ROOT_URL);
  }

}
