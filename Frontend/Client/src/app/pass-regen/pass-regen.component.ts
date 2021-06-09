import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl, FormGroupDirective, NgForm} from '@angular/forms';
import { ErrorMatcher } from '../error-matcher';
import { HeaderServiceService } from '../header-service.service';
import { MatStepper } from '@angular/material/stepper';
import { LoginComponent } from '../login/login.component';
import { AuthServiceService } from '../login/auth-service.service';
import { InfosTransfrService } from '../infos-transfr.service';
@Component({
  selector: 'app-pass-regen',
  templateUrl: './pass-regen.component.html',
  styleUrls: ['./pass-regen.component.css']
})
export class PassRegenComponent implements OnInit {
  isLinear = true;
  hide = true;
  oldPass: FormGroup;
  newPass: FormGroup;

  constructor(private _formBuilder: FormBuilder,
    private headerService : HeaderServiceService,
     private authservice : AuthServiceService,
     private info_ : InfosTransfrService) {}

  ngOnInit() {

    // this.info_.get_id().subscribe((res : number)=>{
    //   this.id_ = res;
    // })
    this.oldPass = this._formBuilder.group({
      firstCtrl: ['', [Validators.required]]
    }, {
      // check whether our password and confirm password match
      validator: this.checkyos
    }
    );
    this.newPass = this._formBuilder.group({
      secondCtrl1: ['', [Validators.required, Validators.minLength(8), Validators.pattern(/^[0-9]\d*$/)]],
      secondCtrl2: ['', [Validators.compose([Validators.required]), Validators.pattern(/^[0-9]\d*$/)]]
    },
    {
      // check whether our password and confirm password match
      validator: this.checkPasswords
    }
    );
    this.headerService.setTitle('Changer son mot de passe');
  }
  matcher = new ErrorMatcher();

  checkPasswords(group: FormGroup) { // here we have the 'passwords' group
    let pass = group.controls.secondCtrl1.value;
    let confirmPass = group.controls.secondCtrl2.value;
    return pass === confirmPass ? null : { notSame: true }
  }

  checkyos(group: FormGroup) { // here we have the 'passwords' group
    let pass = "123321123"
    let confirmPass = group.controls.firstCtrl.value;
    return pass === confirmPass ? null : { notSame: true }
  }
  checkoldpass(stepper : MatStepper, group: FormGroup) { // here we have the 'passwords' group
    let pass = group.controls.firstCtrl.value;
    // let oldpass = this.headerService.password;

    let oldpass = "123321123";
    if(pass === oldpass){
      stepper.next();
    }


  }
  changepass(){
    console.log("entrain de changer mdp");

    let crud = {
      "userName" : sessionStorage.getItem('email'),
      "password" : this.oldPass.value.firstCtrl,
      "newPassword" : this.newPass.value.secondCtrl1,
      "confirmPassword" : this.newPass.value.secondCtrl2,
    }

    //still didnt add the id this function isnt working yet
    this.authservice.passChange(crud,sessionStorage.getItem('email')).subscribe((rsp =>{
      console.log(rsp);

    }),err =>{
      console.log(err);

    })

    }



}
