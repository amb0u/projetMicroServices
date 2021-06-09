import { Component, OnInit } from '@angular/core';
import { HeaderServiceService } from '../header-service.service';
import { InfosTransfrService } from '../infos-transfr.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  test= {
    "id": 0,
    "firstName": "",
    "lastName": "",
    "email": "",
    "address": "",
    "birthDay": "",
    "cin": "JP87256",
    "phoneNumber": "",
    "operator": "",
    "gender": "",
    "status": "",
    "contract": "",
    "creationDate": "",
    "agencyId": 0,
    "currentAccountNum": 1111,
    "savingsAccountNum": 1234
  };
  constructor(private headerService : HeaderServiceService,private info : InfosTransfrService) {
    this.headerService.setTitle("Accueil");
    this.headerService.setIcon("home");
 }

  ngOnInit(): void {
    this.info.call(this.test);
  }

}
