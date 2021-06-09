import { Component, OnInit } from '@angular/core';
import { OprationServiceService } from '../opration-service.service';

@Component({
  selector: 'app-operation-header',
  templateUrl: './operation-header.component.html',
  styleUrls: ['./operation-header.component.css']
})
export class OperationHeaderComponent implements OnInit {

  constructor( private http: OprationServiceService ) { }
  compte: compte ;

  ngOnInit(): void {
    this.http.getCompteInfo().subscribe( data => {
     this.compte = data ;
     console.log(data) ;
    } );
  }

}

export interface compte {
  numero: string ;
  solde: number ;
}
