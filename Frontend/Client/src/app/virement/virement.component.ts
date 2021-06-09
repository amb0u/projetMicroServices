import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { HeaderServiceService } from '../header-service.service';
import { ErrorMatcher } from '../error-matcher';
import { Observable } from 'rxjs';
import { startWith } from 'rxjs/internal/operators/startWith';
import { map } from 'rxjs/internal/operators/map';
import { TaskSubmitService } from '../task-submit.service';
import { InfosTransfrService } from '../infos-transfr.service';
import { Router } from '@angular/router';
import { SideNavComponent } from '../side-nav/side-nav.component';


@Component({
  selector: 'app-virement',
  templateUrl: './virement.component.html',
  styleUrls: ['./virement.component.css']
})
export class VirementComponent implements OnInit {

  User_object = {
    "id": 1,
    "firstName": "Abdou",
    "lastName": "ahban",
    "email": "user@backend.com",
    "address": "Aoulouz, Taroudant",
    "birthDay": "2020-06-21",
    "cin": "JP87256",
    "phoneNumber": "09734567894567",
    "operator": "Orange",
    "gender": "Masculin",
    "status": "Actif",
    "contract": "Personnel",
    "creationDate": "2020-06-21",
    "agencyId": 1,
    "currentAccountNum": 9999,
    "savingsAccountNum": 6666
  }; //all user infos are stored in this object
  ngOnInit(): void {
    this.customerinfos.getinfos().subscribe((res :any)=>{
      this.User_object = res;
    },(err)=>{
      console.log(err);

    })
  }
  filteredAccounts: Observable<any[]>;

  Accounts : any = ["2222","1111"];

  matcher = new ErrorMatcher();
  virementForm = this.fb.group({
    compte: ['', Validators.required],
    beneficiaire: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(4), Validators.pattern(/^[0-9]\d*$/)]],
    amount: ['', Validators.required],
    motif :['', Validators.required]
  })
  @Output('BalanceChanged') BalanceChanged = new EventEmitter<boolean>();

  constructor(
    private fb: FormBuilder,
    private headerService : HeaderServiceService,
    private taskSubmit : TaskSubmitService,
    private customerinfos : InfosTransfrService,
    private router : Router,
    private sidenav : SideNavComponent
    ) {

    this.headerService.setTitle('Effectuer un virement');
    this.headerService.setIcon('money');

    // this.filteredAccounts = this.virementForm.valueChanges
    //   .pipe(
    //      startWith(''),
    //      map(account => account ? this._filterAccounts(account) : this.Accounts.slice())
    //   );

  }
  private _filterAccounts(value: string): any[] {
    const filterValue = value;
    return this.Accounts.filter(account => account.indexOf(filterValue) === 0);
  }

  OnSubmit(){
    let virement_form = {
       "amount": this.virementForm.value.amount,
       "accountNum": this.virementForm.value.compte,
       "destinationAccountNum": this.virementForm.value.beneficiaire,
       "motif": this.virementForm.value.motif
    }
      if(
        virement_form.amount !== "" ||
        virement_form.accountNum !== "" ||
        virement_form.destinationAccountNum !== ""
      ){
        this.taskSubmit.submit_virement(virement_form).subscribe((response : any )=>{
          this.sidenav.update();
          this.sidenav.setSalaire(this.virementForm.value.amount);
      } , (err) =>{
          console.log(err);

      });
          console.log(this.virementForm.value);
          this.router.navigate(['/']);
      }
      else{
        console.log("chi haja khawya");

      }
      this.BalanceChanged.emit(true);

  }

}
