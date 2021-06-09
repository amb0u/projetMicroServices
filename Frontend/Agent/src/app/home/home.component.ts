import { Component, OnInit, Input } from '@angular/core';
import { CustomerServiceService } from '../customer-service.service';
import { PeriodicElement } from './table/table.component';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { InteractionService } from '../interaction.service';
import { SubmitdataService } from 'src/app/submitdata.service';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  constructor(private customerService : CustomerServiceService,
    private interactionService : InteractionService,
    private submitData : SubmitdataService) {
   }


  dataSource ;

  Customers : Array<object> = [];
  nbrClient : number;
  ngOnInit(){
    this.submitData.getData(1).subscribe(data =>{
      this.dataSource = new MatTableDataSource<PeriodicElement>(data);
      this.nbrClient = data.length;
     });

  }

  applyFilter(event: Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }



}
const ELEMENT_DATA: PeriodicElement[] = [
  {prenom: 'amine', nom: 'SEDEGUI', CIN: 'T281837', email: 'amine_sedegui@live.fr'},
  {prenom: 'amine', nom: 'SEDEGUI', CIN: 'T281837', email: 'amine_sedegui@live.fr'},
  {prenom: 'amine', nom: 'SEDEGUI', CIN: 'T281837', email: 'amine_sedegui@live.fr'},
  {prenom: 'abdelghani', nom: 'kadouri', CIN: 'T123231', email: 'abdelghni.kadouri@hotmail.fr'},
  {prenom: 'abdelghani', nom: 'kadouri', CIN: 'T123231', email: 'abdelghni.kadouri@hotmail.fr'},
  {prenom: 'abdelghani', nom: 'kadouri', CIN: 'T123231', email: 'abdelghni.kadouri@hotmail.fr'},
  {prenom: 'mounib', nom: 'elboujbaoui', CIN: 'R342343', email: 'mounib.elboujaoui@hotmail.fr'},
  {prenom: 'mounib', nom: 'elboujbaoui', CIN: 'R342343', email: 'mounib.elboujaoui@hotmail.fr'},
  {prenom: 'mounib', nom: 'elboujbaoui', CIN: 'R342343', email: 'mounib.elboujaoui@hotmail.fr'},
  {prenom: 'abdelilah', nom: 'wahman', CIN: 'L342943', email: 'wahman_abdelilah@hotmail.fr'},

];
