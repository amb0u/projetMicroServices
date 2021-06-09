import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VirementComponent } from './virement/virement.component';
import { RechargeComponent } from './recharge/recharge.component';
import { PassRegenComponent } from './pass-regen/pass-regen.component';
import { HomeComponent } from './home/home.component';
import { OperationsComponent } from './operations/operations.component';
import { ParametreComponent } from './parametre/parametre.component';
import { InfoPersonelleComponent } from './parametre/info-personelle/info-personelle.component';
import { LoginComponent } from './login/login.component';
import { CardsWalletComponent } from './cards-wallet/cards-wallet.component';
import { AccountsComponent } from './accounts/accounts.component';
import { DemandeComponent } from './demande/demande.component';
import { AuthGuard } from './login/auth.guard';
import { OppositionCarteComponent } from './parametre/opposition-carte/opposition-carte.component'
import { VisDemandeComponent } from './vis-demande/vis-demande.component';
import { IResolverService } from './i-resolver.service';





const routes: Routes = [

  { path : '' , component : HomeComponent , canActivate: [AuthGuard] },
  { path : 'login' , component: LoginComponent  },
  { path : 'demande' , component : DemandeComponent , canActivate: [AuthGuard] },
  { path : 'visdemande' , component : VisDemandeComponent , canActivate: [AuthGuard] },
  { path : 'wallet' , component: CardsWalletComponent , canActivate: [AuthGuard] },
  { path : 'operations' , component: OperationsComponent , canActivate: [AuthGuard] },
  { path : 'virement', component : VirementComponent , canActivate: [AuthGuard] },
  { path: 'accounts', component: AccountsComponent , canActivate: [AuthGuard] },
  { path : 'recharge', component : RechargeComponent , canActivate: [AuthGuard] },
  { path : 'regen', component : PassRegenComponent, canActivate: [AuthGuard] },
  { path : 'wallet' , component : CardsWalletComponent , canActivate: [AuthGuard] },
  { path : 'setting' , component : ParametreComponent , canActivate: [AuthGuard] ,
    children: [
      { path : 'informationsPersonelle' , component : InfoPersonelleComponent ,
       resolve:{ infos : IResolverService}},
      { path: 'oppositionCarte', component: OppositionCarteComponent }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
