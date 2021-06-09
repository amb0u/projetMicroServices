import { Component, OnInit, ViewChild, Input, ChangeDetectorRef } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { PeriodicElement, SubmitdataService } from '../submitdata.service';
import { InteractionService } from '../interaction.service';
import { CustomerServiceService } from '../customer-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { VisRepComponent } from './vis-rep/vis-rep.component';
import { VisualiserService } from './visualiser.service';
import { element } from 'protractor';
import { ReqApiService } from './req-api.service';
import { MatTableDataSource } from '@angular/material/table';



@Component({
  selector: 'app-gestion-demande',
  templateUrl: './gestion-demande.component.html',
  styleUrls: ['./gestion-demande.component.css']
})
export class GestionDemandeComponent implements OnInit {

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  dataSource :any ;

  ELEMENT_DATA : PeriodicElement[] ;


  ngOnInit(): void {
    // this.reqapi.get_demandes().subscribe(data =>{
    //   this.dataSource = new MatTableDataSource<PeriodicElement>(data);
    //  });
    this.refresh();
  }

  refresh(){
    this.reqapi.get_demandes().subscribe(data=>{
      console.log(data);
      this.dataSource = new MatTableDataSource<any>(data);
      this.changeDetectorRefs.detectChanges();
    })

  }



  columns: string[] = ['select','dem_id', 'client_id', 'type', 'dateDemande','status','action'];

  constructor(
    private router : Router,
    private interaction : InteractionService,
    private customerService : CustomerServiceService,
    private submitData : SubmitdataService,
    private activated : ActivatedRoute,
    public dialog: MatDialog,
    private visService : VisualiserService,
    private reqapi : ReqApiService,
    private changeDetectorRefs: ChangeDetectorRef
      ){
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.columns, event.previousIndex, event.currentIndex);
  }
  delete( dem_id : number ){
    this.reqapi.delete_demandes(dem_id).subscribe((res)=>{
      this.refresh();
    });
  }

  goVisualiser( val ){
    this.visService.setVariables(val.client_id,val.dem_id,val.type,val.message,val.dateDemande);
    const dialogRef = this.dialog.open(VisRepComponent, {
      width: '1000px',

    });

    dialogRef.afterClosed().subscribe(result => {
      console.log("amine after");
      this.refresh();

    });
  }
  }


