import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ErrorMatcher } from '../error-matcher';
import { HeaderServiceService } from '../header-service.service';
import { TaskSubmitService } from '../task-submit.service';
import { Router } from '@angular/router';
import { InfosTransfrService } from '../infos-transfr.service';
@Component({
  selector: 'app-recharge',
  templateUrl: './recharge.component.html',
  styleUrls: ['./recharge.component.css']
})
export class RechargeComponent implements OnInit {
  User_object = {
    "id": 1,
    "firstName": "",
    "lastName": "",
    "email": "",
    "address": "",
    "birthDay": "",
    "cin": "",
    "phoneNumber": "",
    "operator": "",
    "gender": "",
    "status": "",
    "contract": "",
    "creationDate": "",
    "agencyId": 1,
    "currentAccountNum": 9999,
    "savingsAccountNum": 6666
  }; //all user infos are stored in this object



  // form = {

  //   compte : this.compte,
  //   operateur : this.operateur,
  //   phone : this.phone,
  //   amount : this.amount

  // }






  rechargeForm = this.fb.group({
    compte: ['', Validators.required],
    operateur: ['', Validators.required],
    phone: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern(/^[0-9]\d*$/)]],
    amount: ['', Validators.required]
  });



  matcher = new ErrorMatcher();

  constructor(private fb: FormBuilder,
    private headerService : HeaderServiceService,
    private taskSubmit : TaskSubmitService,
    private router : Router,
    private customerinfos : InfosTransfrService,
    ) {
    this.headerService.setTitle('Effectuer une recharge');
    this.headerService.setIcon('money');
  }

  ngOnInit(){
    this.customerinfos.getinfos().subscribe((res :any)=>{

      this.User_object = res;
    },(err)=>{
      console.log(err);

    })
  }

  // get compte(){
  //   return this.rechargeForm.get('compte');
  // }
  // get operateur(){
  //   return this.rechargeForm.get('operateur');
  // }
  // get phone(){
  //   return this.rechargeForm.get('phone');
  // }
  // get amount(){
  //   return this.rechargeForm.get('amount');
  // }

  OnSubmit(){
    let recharge_Form = {
      "amount": this.rechargeForm.value.amount,
        "accountNum": this.rechargeForm.value.compte,
        "operator": this.rechargeForm.value.operateur,
        "numRecharge": this.rechargeForm.value.phone
    };


      this.taskSubmit.submit_recharge(recharge_Form).subscribe((response : any )=>{
        console.log(response);
    },(err)=>{
      console.log(err);
    });
    this.router.navigate(['/']);


  }





}
