import { Component, OnInit } from '@angular/core';
import { HeaderServiceService } from '../header-service.service';
import { ReqApiService } from '../demande/req-api.service';
import { InfosTransfrService } from '../infos-transfr.service';

@Component({
  selector: 'app-vis-demande',
  templateUrl: './vis-demande.component.html',
  styleUrls: ['./vis-demande.component.css']
})
export class VisDemandeComponent implements OnInit {

  demandes : any = [];
  isSend = false ;
  client_id : number ;
  constructor(private headerService : HeaderServiceService,private reqap : ReqApiService , private info_ : InfosTransfrService) {
    this.headerService.setTitle('Vos Demandes');
    this.headerService.setIcon("email");
   }

  ngOnInit(): void {
    this.isSend = true ;
    this.info_.get_id().subscribe((rsp : number)=>{
      this.reqap.get_demande(rsp).subscribe((res)=>{
        this.isSend = false ;
        this.demandes = res.body;
        console.log(this.demandes);
      },(err)=>{
        console.log(err);


      });

    })

  }

}
