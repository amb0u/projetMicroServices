import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-numpad',
  templateUrl: './numpad.component.html',
  styleUrls: ['./numpad.component.css']
})
export class NumpadComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  type(val : string){
    this.Pass = this.Pass + val;
    console.log(this.Pass);


  }
Pass : String ="";
}
