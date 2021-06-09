import { Component, OnInit } from '@angular/core';
import { transaction } from '../transaction-list/transaction-list.component';
import { HttpHomeService } from '../http-home.service';
import { InfosTransfrService } from 'src/app/infos-transfr.service';

@Component({
  selector: 'app-transaction-list-bar',
  templateUrl: './transaction-list-bar.component.html',
  styleUrls: ['./transaction-list-bar.component.css']
})
export class TransactionListBarComponent implements OnInit {
  info_object ={

  }
  constructor(private httpService: HttpHomeService,private infosapi : InfosTransfrService) {
    this.infosapi.getinfos().subscribe((res :any)=>{
      this.info_object = res;

    },(err)=>{
      console.log(err);

    })
   }
  transaction: transaction[] ;

  ngOnInit(): void {
    this.httpService.getTransactionListDataBar(this.infosapi.info_object.currentAccountNum).subscribe( data => {
      this.transaction = data ;

    }) ;

  }

}
