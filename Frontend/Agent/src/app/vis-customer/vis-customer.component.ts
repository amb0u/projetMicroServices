import { Component, OnInit } from '@angular/core';
import { Customer } from '../models/Customer';
import { CustomerServiceService } from '../customer-service.service'
import { InteractionService } from '../interaction.service';
import {MatDialog} from '@angular/material/dialog';
import {ModCustomerComponent} from '../mod-customer/mod-customer.component';

@Component({
  selector: 'app-vis-customer',
  templateUrl: './vis-customer.component.html',
  styleUrls: ['./vis-customer.component.css']
})
export class VisCustomerComponent implements OnInit {

  customer : Customer;

  constructor(
    private customerService : CustomerServiceService, 
    private interaction : InteractionService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.customer = new Customer();
    this.loadData();
  }


  loadData() {
      let id = this.interaction.client_id ;
      this.customerService.getCustomer(id).subscribe(data => {
        console.log(data);
        this.customer = data;
      },  err => console.log(err));
  }
  
  deleteCustomer() {  
    let id = this.interaction.client_id ;
    this.customerService.deleteCustomer(id).subscribe();
  }

  goModifier() {
    this.dialog.closeAll();
    this.dialog.open(ModCustomerComponent, { width: '550px'})
  }

  suspend() {
    console.log(this.customer.email)
    this.customerService.suspend(this.customer.email).subscribe(res => {
      console.log(res);
    })
  }
}
