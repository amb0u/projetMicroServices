import { Component } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import { RippleGlobalOptions } from '@angular/material/core';
import { AuthServiceService } from './auth-service.service';




@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'e-bank';
  constructor(private auth : AuthServiceService) {}
  isConected() {
    return this.auth.loggedIn();
  }
}

