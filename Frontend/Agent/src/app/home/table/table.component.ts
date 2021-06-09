
import {Component, OnInit, OnChanges, Input, ViewChild} from '@angular/core';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import { MatSort } from '@angular/material/sort';
import { Router, ActivatedRoute } from '@angular/router';
import { InteractionService } from 'src/app/interaction.service';
import { CustomerServiceService } from 'src/app/customer-service.service';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { SubmitdataService } from 'src/app/submitdata.service';
import { MatDialog } from '@angular/material/dialog';
import { VisRepComponent } from 'src/app/gestion-demande/vis-rep/vis-rep.component';
import { VisCustomerComponent } from 'src/app/vis-customer/vis-customer.component';
import { ModCustomerComponent } from 'src/app/mod-customer/mod-customer.component';

/**
 * @title Table with re-orderable columns
 */
export class PeriodicElement {
  nom: string;
  prenom: string;
  CIN: string;
  email: string;
}
@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})

export class TableComponent implements OnInit{

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @Input() dataSource ;

  ELEMENT_DATA : PeriodicElement[] ;


  ngOnInit(): void {

     this.submitData.getData(1).subscribe(data =>{
      this.dataSource = new MatTableDataSource<PeriodicElement>(data);
     });

  }



  columns: string[] = ['select','prenom', 'nom', 'CIN', 'email','action'];

  constructor(private Router : Router,private interaction : InteractionService, private customerService : CustomerServiceService,
    private submitData : SubmitdataService, private activated : ActivatedRoute,public dialog: MatDialog){
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.columns, event.previousIndex, event.currentIndex);
  }

  goModifier(val : string){
     //this.Router.navigate(['/','visualiser', val]);
     this.interaction.setClient_id(val);
     const dialogRef = this.dialog.open(ModCustomerComponent, {
      width: '550px',

    });

    dialogRef.afterClosed().subscribe(result => {
      this.submitData.getData(1).subscribe(data =>{
        this.dataSource = new MatTableDataSource<PeriodicElement>(data);
       });
    });

    console.log(val);


  }
  goVisualiser( val : string ){
    //this.Router.navigate(['/','visualiser', val]);
    this.interaction.setClient_id(val);
    const dialogRef = this.dialog.open(VisCustomerComponent, {
      width: '550px',

    });

    dialogRef.afterClosed().subscribe(result => {
      this.submitData.getData(1).subscribe(data =>{
        this.dataSource = new MatTableDataSource<PeriodicElement>(data);
       });

    });

    console.log(val);

  }
}



