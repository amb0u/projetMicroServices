import { Component, OnInit } from '@angular/core';
import { VisualiserService } from '../visualiser.service';
import { CustomerServiceService } from 'src/app/customer-service.service';
import { ReqApiService } from '../req-api.service';
import { Router } from '@angular/router';
import { GestionDemandeComponent } from '../gestion-demande.component';

@Component({
  selector: 'app-vis-rep',
  templateUrl: './vis-rep.component.html',
  styleUrls: ['./vis-rep.component.css']
})
export class VisRepComponent implements OnInit {

  constructor(private visService : VisualiserService,
    private customerserv : CustomerServiceService,
    private reqapi : ReqApiService,
    private router : Router) { }

  client_id : number ;
  dem_id : number;
  type : string = "";
  message : string = "";
  date :string = "";
  nom :string = "";
  prenom :string = "";

  ngOnInit(): void {


    this.client_id = this.visService.client_id;
    this.dem_id = this.visService.dem_id;
    this.type = this.visService.type;
    this.message = this.visService.message;
    this.date = this.visService.date;
    this.customerserv.getCustomer(this.client_id).subscribe((res)=>{
      this.prenom = res.firstName;
      this.nom = res.lastName;

    })

  }
  traiter(){
    this.reqapi.traiter(this.visService.dem_id).subscribe((res=>{


    }),(err=>{
      console.log(err);

    }))

  }


}
