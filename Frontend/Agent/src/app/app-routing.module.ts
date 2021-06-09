import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { SideNavComponent } from './side-nav/side-nav.component';

import { FormClientComponent } from './agent/form-client/form-client.component';
import { FormAccountComponent } from './agent/form-account/form-account.component';
import { ModCustomerComponent } from './mod-customer/mod-customer.component';
import { HomeComponent } from './home/home.component';
import { VisCustomerComponent } from './vis-customer/vis-customer.component';

import { GestionDemandeComponent } from './gestion-demande/gestion-demande.component';
// import { DashboardComponent } from './agent/dashboard/dashboard.component';



const routes: Routes = [
  {
  path : 'login' , component: LoginComponent
},
  {path : '' , component: HomeComponent,

  },

  {path : 'ajouter' , component: FormClientComponent} ,
  {path : 'acount' , component: FormAccountComponent} ,
  {path : 'modifier/:id' , component: ModCustomerComponent},
  {path : 'visualiser/:id' , component: VisCustomerComponent},
  {path : 'demandes' , component: GestionDemandeComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
