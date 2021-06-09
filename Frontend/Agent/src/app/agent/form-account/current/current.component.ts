import { Component, OnInit, EventEmitter, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpReqService } from '../../http-req.service';

@Component({
  selector: 'app-current',
  templateUrl: './current.component.html',
  styleUrls: ['./current.component.css']
})
export class CurrentComponent implements OnInit {

  constructor(private httpService: HttpReqService) { }
  @Output()
  issiwtched =  new EventEmitter<boolean>() ;

  @ViewChild('current')
  current: NgForm ;
  currentAccount: CurrentCompte ;
  isSend = false ;
  ishidden = true;

  onSwitch() {
    this.issiwtched.emit(true) ;
  }

  private getFormData() {
    const f = this.current.form.value ;
    let date = new Date();
    this.currentAccount = {
      accountNum: f.Numc  , accountOpeningDate: "2020-06-29" , accountBalance: f.solde , accountLimit: f.Limitc ,
      clientId: 3
    } ;

    console.log(this.currentAccount) ;
  }

  onSubmit() {
    this.isSend = true ;
    this.getFormData() ;
    this.httpService.addCurentAccount(this.currentAccount).subscribe( data => { this.isSend = false ; } ,
       error => {
         this.isSend = false ;
         this.ishidden = false ;
       } )
  }

  ngOnInit(): void {
  }

}

export interface CurrentCompte {
  accountNum: number ; accountOpeningDate: string ; accountBalance: number ; accountLimit: number ;
  clientId: number;
}
