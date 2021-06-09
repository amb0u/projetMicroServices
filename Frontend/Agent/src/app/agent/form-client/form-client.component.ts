import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpReqService } from '../http-req.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-client',
  templateUrl: './form-client.component.html',
  styleUrls: ['./form-client.component.css']
})
export class FormClientComponent implements OnInit {

  constructor(private httpService: HttpReqService,private route : Router) { }
  @ViewChild('addClient')
  clientForm: NgForm ;
  client:any ;
  tele: number ;
  isSend = false ;
  ishidden = true;

  ngOnInit(): void {
  }

  private getClient() {
    const f = this.clientForm.value ;
    this.client = {
      firstName: f.name , lastName: f.prenom , email: f.email , address: f.adress , birthDay: f.birth , cin: f.cin ,
      phoneNumber: f.phone , gender: f.sexe , status: f.status , contract: f.contrat ,operator : f.operator,
      agencyId: 1
    } ;
  }

  onSubmit() {
    this.isSend = true ;
    this.getClient() ;
    console.log( this.client) ;
    this.httpService.AddClient(this.client)
    .subscribe( data => {
      this.isSend = false ;
      this.route.navigateByUrl('/');
    }
       , error => {
          console.log(error);
          this.isSend = false ;
          this.ishidden = false;
      } );
  }

}

export interface Client {
  firstName: string ; lastName: string ; email: string ; address: string ; birthDay: Date ;
  cin: string ; phoneNumber: string ; gender: string ; status: string ; contract: string ; creationDate: Date ;
  agencyId: number ;
}
