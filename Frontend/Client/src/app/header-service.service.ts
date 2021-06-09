import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';



@Injectable({
  providedIn: 'root'
})
export class HeaderServiceService {
  public title = new BehaviorSubject('');
  public icon = new BehaviorSubject('');
  public password = "123";
  constructor() { }

  setTitle(title : string) {
    this.title.next(title);
  }
  setIcon(icon : string){
    this.icon.next(icon);
  }
  setPassword(pass : string){
    this.password = pass;
  }
}
