import { Injectable, IterableDiffers } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map} from 'rxjs/operators' ;
import { operation } from './operation-section/operation-section.component';
import { compte } from './operation-header/operation-header.component';
import { InfosTransfrService } from '../infos-transfr.service';

@Injectable({
  providedIn: 'root'
})
export class OprationServiceService {
  //ClientId = '6' ;
  test= {}
  constructor(private http: HttpClient , private infoService: InfosTransfrService) { }

  getOperationData() {
    this.infoService.call(this.test)
    return this.http.get('http://localhost:8083/api/account/virements/' + this.infoService.info_object.currentAccountNum).
    pipe(map( data => {
      const operations = [] ;
      // tslint:disable-next-line: forin
      for ( const key in data) {
        operations.push(
          {   date : data[key].transactionDate ,
              benificiaire :data[key].destinationAccountNum ,
              montant : data[key].amount ,
              description: data[key].motif
          }
        ) ;
      }
      return operations ;
    })
    );
  }


  getRechargHistory() {
    this.infoService.call(this.test)
    return this.http.get('http://localhost:8083/api/account/recharges/ ' + this.infoService.info_object.currentAccountNum).pipe(map( recharges => {
      const recharg = [] ;
      // tslint:disable-next-line: forin
      for (const key in recharges) {
        recharg.push(
          {
            date: recharges[key].transactionDate ,
            benificiaire: recharges[key].numRecharge ,
            montant: recharges[key].amount ,
            description: recharges[key].operator
          }
        ) ;
      }
      return recharg ;
    } )
    );
  }

  getCompteInfo() {
   this.infoService.call(this.test)
   return this.http.get('http://localhost:8082/api/currentAccounts/accountNum/' + this.infoService.info_object.currentAccountNum).
   pipe(map( data => {
     // tslint:disable-next-line: no-shadowed-variable
     let compt ;
     compt = { numero : data['accountNum'] , solde: data['accountBalance']} ;
     return compt ;
    })
    );
  }
}
