import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ErrorMatcher } from '../error-matcher';
import { HeaderServiceService } from '../header-service.service';
import { ReqApiService } from './req-api.service';
//import { TaskSubmitService } from '../task-submit.service';
@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  styleUrls: ['./demande.component.css']
})
export class DemandeComponent implements OnInit {
  hidden_valide = true;
  constructor(
    private fb : FormBuilder,
    private headerService : HeaderServiceService,
    private reqservice : ReqApiService
    ) {
      this.headerService.setTitle("Faire une Demande");
      this.headerService.setIcon("email");
     }

  ngOnInit(): void {
  }
  demandeForm = this.fb.group({
    typeDemande: ['', Validators.required],
    demandeText: ['', Validators.required]
  });
  OnSubmit(){
    //this.taskSubmit.submit_demande(this.rechargeForm.value).subscribe((response : any )=>{
      ////stuff

  //});
  let demande = {
    "message": this.demandeForm.value.demandeText,
    "type":this.demandeForm.value.typeDemande

  };
  console.log(demande);
  this.reqservice.faire_demande(demande).subscribe((res)=>{
    console.log(res);
    this.hidden_valide = false;

  })
  this.demandeForm.reset()


}

}
