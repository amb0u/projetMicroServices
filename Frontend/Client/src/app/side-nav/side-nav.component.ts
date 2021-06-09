import { Component, OnInit, AfterContentChecked, AfterViewChecked, OnChanges } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { HeaderServiceService } from '../header-service.service';
import { Router } from '@angular/router';
import { AuthServiceService } from '../login/auth-service.service';
import { InfosTransfrService } from '../infos-transfr.service';
import { AccountsServiceService } from '../accounts-service.service';



@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent implements OnInit//,AfterContentChecked
{

  Title : string = "Home";
  Icon : string = "Home";
  Balance : any = "";
  id = 0;
  info_object :object ={};

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,
              private headerservice : HeaderServiceService,
              private Router : Router,
              private auth : AuthServiceService,
              private customer : InfosTransfrService,
              private accountserv : AccountsServiceService
              ) {
                this.headerservice.title.subscribe(title =>{
                  this.Title =title;
                });
                this.headerservice.icon.subscribe(icon =>{
                  this.Icon =icon;
                });
                // this.customer.getinfos().subscribe((res :any)=>{
                //   this.id = res.id;
                // },(err)=>{
                //   console.log(err);

                // });


              }

  SetTitle(name : string){
    this.Title=name;
  }
  changeBal(){
    console.log("heere");

    this.customer.get_id().subscribe((res : number)=>{
      this.accountserv.getAccount(res).subscribe((res:any)=>{
        this.Balance = res.accountBalance;
      });
    })
  }
  ngOnInit(): void {
    this.update();
    this.customer.call(this.info_object);
  }
  goDemande(){
    this.Router.navigateByUrl('demande');
  }
  setSalaire(cu : any){
     this.Balance = parseFloat(this.Balance) - parseFloat(cu);

  }
  update(){
    this.customer.get_id().subscribe((res : number)=>{
      this.accountserv.getAccount(res).subscribe((res:any)=>{
        this.Balance = res.accountBalance;
      });
    })
  }

  goOperations() {
    this.Router.navigateByUrl('operations');
  }
  disconnect(){
    this.auth.logout();

  }
  caller(){
    this.Router.navigateByUrl('visdemande');
  }
}
