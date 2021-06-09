import { Injectable } from '@angular/core';
import { WebRequestService } from './web-request.service';

@Injectable({
  providedIn: 'root'
})
export class TaskSubmitService {

  constructor(private webReqService : WebRequestService) { }

  submit_virement(objet : object){
    return this.webReqService.post('virements',objet);
  }
  submit_recharge(objet : object){
    return this.webReqService.post('recharges',objet);
  }
  submit_pass(objet : object){
    return this.webReqService.post('zzzz',objet);
  }
}
