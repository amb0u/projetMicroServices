import { Component, OnInit  } from '@angular/core';
import { HttpHomeService } from '../http-home.service';
import { InfosTransfrService } from 'src/app/infos-transfr.service';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit {
  info_object :  any= {

  };
  constructor(private infosapi : InfosTransfrService,private httpService: HttpHomeService) {
    this.infosapi.getinfos().subscribe((res :any)=>{
      this.info_object = res;

    },(err)=>{
      console.log(err);

    })
  }
  transaction: transaction[] ;

  ngOnInit(): void {

    this.httpService.getTransactionListDataLine(this.infosapi.info_object.currentAccountNum).subscribe( data => {
        this.transaction =  data ;
    }
    ) ;
  }

}

export interface transaction {
  num: number ,
  description: string ,
  date: string ,
  price: number
}
