import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-expansion-list',
  templateUrl: './expansion-list.component.html',
  styleUrls: ['./expansion-list.component.css']
})
export class ExpansionListComponent implements OnInit {

  constructor( private router: Router) { }

  ngOnInit(): void {
  }

  items: string[] = [ "Sur carte", "Sur cheque"];
  toggle = [];

}
