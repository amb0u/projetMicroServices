import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private auth: AuthServiceService , private router: Router, private fb : FormBuilder,
    ) { }
  Pass : string="";
  test :object = {};
  ngOnInit(): void {
  }

  loginForm = this.fb.group({
    login: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(16), Validators.maxLength(16), Validators.pattern(/^[0-9]\d*$/)]],
  })


  Login() {
    let cred = {
      "userName" : this.loginForm.value.login,
      "password" : this.loginForm.value.password
    }

    this.auth.login(cred).subscribe((rsp : HttpResponse<any>)=>{
      let token = rsp.headers.get('Authorization').split(' ')[1];
      sessionStorage.setItem('token', token);
      this.router.navigate(['/']);

    },
    (err )=>{
      console.log(err);
    });
  }
  type(val : string){
    this.Pass = this.Pass + val;
    console.log(this.Pass);
  }
  reset(){
    this.Pass = "";
  }
  backspace(){
    this.Pass = this.Pass.slice(0, -1);
    console.log(this.Pass);


  }
}
