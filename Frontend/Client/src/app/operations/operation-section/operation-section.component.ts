import { Component, OnInit , ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { OprationServiceService } from '../opration-service.service';

@Component({
  selector: 'app-operation-section',
  templateUrl: './operation-section.component.html',
  styleUrls: ['./operation-section.component.css']
})
export class OperationSectionComponent implements OnInit {

  constructor( private operationService: OprationServiceService) { }
  displayedColumns: string[] = ['Date', 'Benificiaire', 'Montant'];
  //dataSource = new MatTableDataSource<operation>(ELEMENT_DATA);
  dataSource ;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  ngOnInit(): void {
    this.operationService.getOperationData().subscribe(data => {
        this.dataSource = new MatTableDataSource<operation>(data) ;
        this.dataSource.paginator = this.paginator;
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

/*const ELEMENT_DATA: operation[] = [
 {date: new Date() , benificiaire: 'ghani' , montant: 2000 , description: 'COMMISSION FACT TAXES EN LIGNE'},
 {date: new Date() , benificiaire: 'kad' , montant: 4000 ,  description: 'COMMISSION FACT TAXES EN LIGNE'},
 {date: new Date() , benificiaire: 'amine' , montant: 5000 ,  description: 'COMMISSION FACT TAXES EN LIGNE'},
 {date: new Date() , benificiaire: 'mounib' , montant: 6000,  description: 'COMMISSION FACT TAXES EN LIGNE'},
 {date: new Date() , benificiaire: 'abdo' , montant: 2000 ,  description: 'COMMISSION FACT TAXES EN LIGNE'},
 {date: new Date() , benificiaire: 'lahlou' , montant: 2000 ,  description: 'COMMISSION FACT TAXES EN LIGNE'},
];*/

