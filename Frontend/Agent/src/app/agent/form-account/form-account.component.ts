import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-account',
  templateUrl: './form-account.component.html',
  styleUrls: ['./form-account.component.css']
})
export class FormAccountComponent implements OnInit {

  constructor() { }
  isswitched = true ;

  onSwitch(event: boolean) {
    if (this.isswitched) {
      this.isswitched = false ;
    } else {
      this.isswitched = true ;
    }
  }

  ngOnInit(): void {
  }

}
