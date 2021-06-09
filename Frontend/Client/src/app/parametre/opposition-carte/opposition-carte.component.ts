import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-opposition-carte',
  templateUrl: './opposition-carte.component.html',
  styleUrls: ['./opposition-carte.component.css']
})
export class OppositionCarteComponent implements OnInit {
  model: Card;
  motif: string;

  constructor() {}

  ngOnInit() {
    this.model = new Card("", "");
  }

  get diagnostic() { return JSON.stringify(this.motif); }
  
  submit(): void {
    console.log("Submitted");
  }



  cards: Card[] = [
    new Card("0305200232229071", "Amex"),
    new Card("4985151449927088", "Visa"),
    new Card("5321112965226022", "MasterCard"),
    new Card("9337858169195984", "Visa"),
    new Card("6087221027940212", "Visa"),
    new Card("2632659657257900", "MasterCard"),
    new Card("2026915664062059", "Amex"),
  ]

  motifs: string[] = [
    "Perte",
    "Vol",
    "Utilisation frauduleuse",
    "Autre ..."
  ]
}

export class Card {
  constructor(
    public number: string,
    public name: string,
  ) {  }
}


