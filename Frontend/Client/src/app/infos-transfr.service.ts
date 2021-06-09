import { Injectable } from '@angular/core';
import { ApiAccountService } from './api-account.service';
import { callbackify } from 'util';
import { share, delay } from 'rxjs/operators';
import { AccountsServiceService } from './accounts-service.service';

@Injectable({
  providedIn: 'root'
})
export class InfosTransfrService {

  salaire : string;
  constructor(private service : ApiAccountService,private accountserv : AccountsServiceService) { }
    // tslint:disable-next-line: variable-name
  info_object= {
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
    getinfos(){
    return this.service.get('customerByEmail/' + sessionStorage.getItem('email'))
    //.pipe(share(),delay(2000));
  }
  get_id(){
    return this.service.get('getMyId');
  }

  get_account_num(){
    return this.info_object.currentAccountNum;
  }

  call(test : object){
    this.getinfos().subscribe((res :any)=>{
      this.info_object = res;
      test = res;

    },(err)=>{
      console.log(err);

    })
  }

}
