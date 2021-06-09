import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { MaterialModule } from './material/material.module';
import {MatButtonModule} from '@angular/material/button';



import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SideNavComponent } from './side-nav/side-nav.component';
import { HomeComponent } from './home/home.component';
import { TableComponent } from './home/table/table.component';



import { FormClientComponent } from './agent/form-client/form-client.component';
import { FormAccountComponent } from './agent/form-account/form-account.component';
// import { DashboardComponent } from './agent/dashboard/dashboard.component';
import { CurrentComponent } from './agent/form-account/current/current.component';
import { SavingComponent } from './agent/form-account/saving/saving.component';
import { VisCustomerComponent } from './vis-customer/vis-customer.component';
import { ModCustomerComponent } from './mod-customer/mod-customer.component';
import { NumpadComponent } from './login/numpad/numpad.component';
import { LoginComponent } from './login/login.component';
import { GestionDemandeComponent } from './gestion-demande/gestion-demande.component';
import { VisRepComponent } from './gestion-demande/vis-rep/vis-rep.component';
import { StarterComponent } from './starter/starter.component';
import { TokenInterceptorService } from './token-interceptor.service';



@NgModule({
  declarations: [
    LoginComponent,
    AppComponent,
    SideNavComponent,
    HomeComponent,
    TableComponent,
    FormClientComponent,
    FormAccountComponent,
    CurrentComponent,
    SavingComponent,
    VisCustomerComponent,
    ModCustomerComponent,
    NumpadComponent,
    GestionDemandeComponent,
    VisRepComponent,
    StarterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MaterialModule
  ],
  providers: [ {
    provide : HTTP_INTERCEPTORS,
    useClass : TokenInterceptorService,
    multi : true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
