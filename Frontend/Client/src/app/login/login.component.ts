import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from './auth-service.service';
import { HttpResponse } from '@angular/common/http';
import { HeaderServiceService } from '../header-service.service';
import { InfosTransfrService } from '../infos-transfr.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ishidden = true;
  constructor(
    private auth: AuthServiceService , private router: Router, private fb : FormBuilder,
    private header : HeaderServiceService,
    private customerinfo : InfosTransfrService) { }
  Pass : string="";


  test= {
    "id": 0,
    "firstName": "",
    "lastName": "",
    "email": "",
    "address": "",
    "birthDay": "",
    "cin": "JP87256",
    "phoneNumber": "",
    "operator": "",
    "gender": "",
    "status": "",
    "contract": "",
    "creationDate": "",
    "agencyId": 0,
    "currentAccountNum": 1111,
    "savingsAccountNum": 1234
  };
  ngOnInit(): void {
  }

  loginForm = this.fb.group({
    login: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(16), Validators.maxLength(16), Validators.pattern(/^[0-9]\d*$/)]],
  })

  Login() {
    let cred = {
      "userName" : this.loginForm.value.login,
      "password" : this.Pass,
    }
    this.header.setPassword(this.Pass);
    this.customerinfo.call(this.test);

    this.auth.login(cred).subscribe((rsp : HttpResponse<any>)=>{
      let token = rsp.headers.get('Authorization').split(' ')[1];
      sessionStorage.setItem('token', token);
      sessionStorage.setItem('email', this.loginForm.value.login);
      this.router.navigate(['/']);
      this.customerinfo.call(this.test);

    },
    (err )=>{
      this.ishidden = false;
      console.log(err);
    });

  }
  type(val : string){
    this.Pass = this.Pass + val;

  }
  reset(){
    this.Pass = "";
  }
  backspace(){
    this.Pass = this.Pass.slice(0, -1);



  }
}
