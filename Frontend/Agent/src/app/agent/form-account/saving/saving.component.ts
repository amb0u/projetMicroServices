import { Component, OnInit , EventEmitter, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpReqService } from '../../http-req.service';

@Component({
  selector: 'app-saving',
  templateUrl: './saving.component.html',
  styleUrls: ['./saving.component.css']
})
export class SavingComponent implements OnInit {
  @Output()
  issiwtched = new EventEmitter<boolean>() ;
  constructor( private httpService: HttpReqService) { }
  @ViewChild('saving')
  saving: NgForm ;
  savingAccount: SavingAccount ;
  isSend = false ;
  ishidden = true;

  onSwitch() {
    this.issiwtched.emit(false) ;
  }
  private getFormData() {
    const f = this.saving.form.value ;
    this.savingAccount = {
      accountNum: f.Numc  , accountOpeningDate: new Date() , accountBalance: f.solde , accountLimit: f.Limitc ,
      clientId: 12 , rate: f.rate
    } ;

    console.log(this.savingAccount) ;
  }

  onSubmit() {
    this.getFormData() ;
    this.isSend = true ;
    this.httpService.addSavingAccount(this.savingAccount).subscribe(data => {
      this.isSend = false ;

     } , error => {
       this.isSend = false ;
       this.ishidden = false;
     }) ;

  }
  ngOnInit(): void {
  }

}

export interface SavingAccount {
  accountNum: number ; accountOpeningDate: Date ; accountBalance: number ; accountLimit: number ;
  clientId: number ; rate: number ;
}
