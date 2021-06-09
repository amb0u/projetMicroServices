import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatSliderModule } from '@angular/material/slider';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatInputModule} from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { SideNavComponent } from './side-nav/side-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {MatExpansionModule} from '@angular/material/expansion';
import { ExpansionListComponent } from './expansion-list/expansion-list.component';
import { VirementComponent } from './virement/virement.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { RechargeComponent } from './recharge/recharge.component';
import { PassRegenComponent } from './pass-regen/pass-regen.component';
import { MatStepperModule } from '@angular/material/stepper';
import { HomeComponent } from './home/home.component';
import { BarChartComponent } from './home/bar-chart/bar-chart.component';
import { LineChartComponent } from './home/line-chart/line-chart.component';
import { TransactionListComponent } from './home/transaction-list/transaction-list.component';
import { OperationsComponent } from './operations/operations.component';
import { OperationHeaderComponent } from './operations/operation-header/operation-header.component';
import { OperationSectionComponent } from './operations/operation-section/operation-section.component';
import { MaterialModule } from './material/material.module';
import { ParametreComponent } from './parametre/parametre.component';
import { InfoPersonelleComponent } from './parametre/info-personelle/info-personelle.component';
import { MAT_RIPPLE_GLOBAL_OPTIONS, RippleGlobalOptions } from '@angular/material/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { DemandeComponent } from './demande/demande.component';
import { CommonModule } from "@angular/common";
import { AccountsComponent } from './accounts/accounts.component';
import { TransactionListBarComponent } from './home/transaction-list-bar/transaction-list-bar.component';
import { LoginComponent } from './login/login.component';
import { RechargeHistoryComponent } from './operations/recharge-history/recharge-history.component';
import { OppositionCarteComponent } from './parametre/opposition-carte/opposition-carte.component';
import { StarterComponent } from './starter/starter.component';
import { AuthServiceService } from './login/auth-service.service';
import { TokenInterceptorService } from './login/token-interceptor.service';
import { VisDemandeComponent } from './vis-demande/vis-demande.component';





const globalRippleConfig: RippleGlobalOptions = {
  disabled: false,
  animation: {
    enterDuration: 300,
    exitDuration: 0
  }
};

@NgModule({
  declarations: [
    AccountsComponent,
    RechargeComponent,
    AppComponent,
    SideNavComponent,
    ExpansionListComponent,
    VirementComponent,
    PassRegenComponent,
    HomeComponent,
    BarChartComponent,
    LineChartComponent,
    TransactionListComponent,
    OperationsComponent,
    OperationHeaderComponent,
    OperationSectionComponent,
    ParametreComponent,
    InfoPersonelleComponent,
    DemandeComponent,
    TransactionListBarComponent,
    LoginComponent,
    RechargeHistoryComponent,
    OppositionCarteComponent,
    StarterComponent,
    VisDemandeComponent
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    LayoutModule,
    HttpClientModule,
    MaterialModule,
    MatStepperModule
  ],
  providers: [
    {provide: MAT_RIPPLE_GLOBAL_OPTIONS, useValue: globalRippleConfig},
    AuthServiceService,// AuthGuard
    , {
      provide : HTTP_INTERCEPTORS,
      useClass : TokenInterceptorService,
      multi : true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
