import { Component, OnInit } from '@angular/core';
import { InfosTransfrService } from 'src/app/infos-transfr.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-info-personelle',
  templateUrl: './info-personelle.component.html',
  styleUrls: ['./info-personelle.component.css']
})
export class InfoPersonelleComponent implements OnInit {
  info_object :  any= {

  };
  constructor(private infosapi : InfosTransfrService,private activatedroute : ActivatedRoute) {
    //this.info_object = this.infosapi.info_object;
    this.infosapi.getinfos().subscribe((res :any)=>{
      this.info_object = res;

    },(err)=>{
      console.log(err);

    })
  }

  dataInfo = [
  ];

  ngOnInit(): void {
    this.activatedroute.data.subscribe((data)=>{
      console.log(data);
      this.info_object=data;
      let datsa  = [
        {cara: 'Nom', value: this.infosapi.info_object.lastName},
        {cara: 'Prenom', value: this.infosapi.info_object.firstName},
        {cara: 'N de tele', value: this.infosapi.info_object.phoneNumber},
        {cara: 'adresse', value: this.infosapi.info_object.address},
        {cara: 'status', value: this.infosapi.info_object.status},
        {cara: 'Email', value: this.infosapi.info_object.email},
        {cara: 'Operateur', value: this.infosapi.info_object.operator}
      ];
      this.dataInfo = datsa;
    })
  }
}
