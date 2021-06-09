import { Component, OnInit } from '@angular/core';
import { Customer } from '../models/Customer';
import { CustomerServiceService } from '../customer-service.service'
import { InteractionService } from '../interaction.service';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-mod-customer',
  templateUrl: './mod-customer.component.html',
  styleUrls: ['./mod-customer.component.css']
})
export class ModCustomerComponent implements OnInit {

  customer : Customer;

  constructor(private customerService : CustomerServiceService, private interaction : InteractionService, private dialog : MatDialog) { }

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

  updateCustomer() {
    this.customer.lastName = (<HTMLInputElement>document.getElementById("lastName")).value;
    this.customer.firstName = (<HTMLInputElement>document.getElementById("firstName")).value;
    this.customer.email = (<HTMLInputElement>document.getElementById("email")).value;
    this.customer.phoneNumber = (<HTMLInputElement>document.getElementById("phoneNumber")).value;
    this.customer.address = (<HTMLInputElement>document.getElementById("address")).value;
    this.customerService.updateCustomer(this.customer.id, this.customer);
    this.dialog.closeAll();
  }

  closeDialog() {
    this.dialog.closeAll();
  }
}
