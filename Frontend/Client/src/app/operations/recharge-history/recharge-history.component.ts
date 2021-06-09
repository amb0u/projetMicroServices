import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { OprationServiceService } from '../opration-service.service';

@Component({
  selector: 'app-recharge-history',
  templateUrl: './recharge-history.component.html',
  styleUrls: ['./recharge-history.component.css']
})
export class RechargeHistoryComponent implements OnInit {

  constructor( private operationService: OprationServiceService) { }
  displayedColumns: string[] = ['Date', 'Benificiaire', 'Montant'];
  //dataSource = new MatTableDataSource<operation>(ELEMENT_DATA);
  dataSource ;
  isSend = false;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  ngOnInit(): void {
    this.isSend = true ;
    this.operationService.getRechargHistory().subscribe(data => {
        this.isSend = false ;
        this.dataSource = new MatTableDataSource<operation>(data) ;
        this.dataSource.paginator = this.paginator;

    },(err)=>{
      this.isSend = false ;
    }) ;
  // this.dataSource.paginator = this.paginator;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}

export interface operation {
  date: Date;
  benificiaire: string;
  montant: Number;
  description: string ;
}


