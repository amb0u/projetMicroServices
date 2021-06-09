import { Component, OnInit } from '@angular/core';
import { HeaderServiceService } from '../header-service.service';

@Component({
  selector: 'app-parametre',
  templateUrl: './parametre.component.html',
  styleUrls: ['./parametre.component.css']
})
export class ParametreComponent implements OnInit {

  constructor(private headerService : HeaderServiceService) {
    this.headerService.setTitle("Options");
    this.headerService.setIcon("settings")
  }
  iconForward = "keyboard_arrow_right" ;
  iconDown = "expand_more " ;
  index;

 iconToShow = [this.iconForward , this.iconForward , this.iconForward , this.iconForward] ;

  ngOnInit(): void {

  }

  onshowIcon(index) {
    if (this.iconToShow[index-1] === this.iconForward) {
      this.iconToShow[index-1] = this.iconDown ;
    } else if (this.iconToShow[index-1] ===  this.iconDown) {
      this.iconToShow[index-1] = this.iconForward ;
    }

    for (let i = 0 ; i < this.iconToShow.length ; i++) {
      if (i !== index-1) {
        this.iconToShow[i] = this.iconForward ;
      }
    }

    this.index = index ;

  }

}
