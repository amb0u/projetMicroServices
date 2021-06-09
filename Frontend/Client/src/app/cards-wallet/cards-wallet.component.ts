import { Component, OnInit } from '@angular/core';


export class Card {
  name: String;
  number: String;
  status: String;
  finvalidite: String;
}

@Component({
  selector: 'app-cards-wallet',
  templateUrl: './cards-wallet.component.html',
  styleUrls: ['./cards-wallet.component.css']
})
export class CardsWalletComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  cards : Card[] = [
    {name: "LIVE", number: "**** **** **** 2562", status: 'Active', finvalidite:"11/22"},
    {name: "LIVE", number: "**** **** **** 2562", status: 'Active', finvalidite:"11/22"},
    {name: "LIVE", number: "**** **** **** 2562", status: 'Active', finvalidite:"11/22"},
    {name: "LIVE", number: "**** **** **** 2562", status: 'Active', finvalidite:"11/22"},
    {name: "LIVE", number: "**** **** **** 2562", status: 'Active', finvalidite:"11/22"},
  ]

  

}

