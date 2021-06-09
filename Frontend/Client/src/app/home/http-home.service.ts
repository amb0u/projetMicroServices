import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map, take} from 'rxjs/operators';
import { dataBarChart } from './bar-chart/bar-chart.component';
import { lineChartData } from './line-chart/line-chart.component';
import { transaction } from './transaction-list/transaction-list.component';
import { InfosTransfrService } from '../infos-transfr.service';
import { AccountsServiceService } from '../accounts-service.service';



@Injectable({
  providedIn: 'root'
})
export class HttpHomeService {
  info_object :  any= {

  };

  constructor(private http: HttpClient, private infoService : InfosTransfrService,private account:AccountsServiceService) {
    this.infoService.getinfos().subscribe((res :any)=>{
      this.info_object = res;

    },(err)=>{
      console.log(err);

    })


   }
  dataBar: dataBarChart ;
  dataLine: lineChartData ;
  transactionBar: transaction[];
  transactionLine: transaction[] ;
  currentAccountNum = this.infoService.get_account_num

  getTransactionListDataLine(accountnum : number) {
    return this.http.get('http://localhost:8083/api/account/3LastVirements/' + accountnum).
    pipe(map((data : any) => {
      let transactions = [] ;
      for (let i = 0 ; i < data.length  ; i++) {
  let tran = {num : data[i].accountNum , description : data[i].motif , date : data[i].trasactionDate , price : data[i].remainingBalance } ;
  transactions.push(tran) ;
      }
      return transactions ;
    })
    );
  }


  getLineData(accountnum : number) {
    return this.http.get('http://localhost:8083/api/account/3LastVirements/' + accountnum).
    pipe(map( dataa => {
      let dataBalance = [] ;
      let labelData = [] ;
      let theData ;
      // tslint:disable-next-line: forin
      for (let i = 0 ; i < 3 ; i++) {
        dataBalance.push( dataa[i].remainingBalance) ;
        labelData.push(dataa[i].transactionDate);
      }
      theData = {labels : labelData , data : dataBalance} ;
      console.log(theData);
      return theData ;
    })
    );
  }


  getBarData(accountnum : number) {
    this.transactionLine = [] ;
    return this.http.get('http://localhost:8083/api/account/incomeOutcome/' + accountnum).
    pipe(map( data => {
      let theDataa = [] ;
      let income = [] ;
      let outcome = [] ;
      let labelse = [];
      for (let i = 0 ; i < 3 ; i++) {
        income.push(data[i].income);
        outcome.push(data[i].outcome);
        labelse.push(data[i].month) ;
      }

      theDataa = [
        { label: "income" ,  backgroundColor: 'rgb(44, 68, 148)', data: income },
        {label: "outcome",  backgroundColor: 'rgb(234, 129, 139)', data: outcome }
      ];

      let BareData = {
        theData: theDataa ,
        labels : labelse
      };
      return BareData ;

    })
    );
  }


  getTransactionListDataBar(accountnum :number) {
    this.transactionBar = [] ;
    return this.http.get('http://localhost:8083/api/account/incomeOutcome/' + accountnum).
    pipe( map( data => {
      let transactions = [] ;
      for (let i = 0 ; i < 3 ; i++) {
  let tran = {num : data[i].accountNum , description : '' , date : data[i].month , price : data[i].balance } ;
  transactions.push(tran) ;
      }
      return transactions ;
    })
    );
  }
}
