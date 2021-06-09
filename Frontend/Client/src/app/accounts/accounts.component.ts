import { Component, OnInit } from '@angular/core';



import { Account } from '../models/Account';
import { SavingsAccount } from '../models/SavingsAccount';
import { AccountsServiceService } from '../accounts-service.service';
import { HeaderServiceService } from '../header-service.service';
import { Transaction } from '../models/Transaction';
import { TransactionService } from '../transaction.service';
import { GetCustomerIdService } from '../getid/get-customer-id.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  principal : number;
  account : Account;
  savings_account : SavingsAccount;
  displayed_transactions: Transaction[];

  constructor(
    private accountsService : AccountsServiceService,
    private headerService : HeaderServiceService,
    private transactionService: TransactionService,
    private customerIdService: GetCustomerIdService
    ) {
      this.headerService.setTitle("Comptes");
      this.headerService.setIcon("account_circle");
     }

  ngOnInit(): void {
    this.account = new Account();
    this.savings_account = new SavingsAccount();
    this.customerIdService.getId().subscribe(res => {
      this.principal = +res.toString();
      this.loadSavingsAccountData(this.principal);
      this.loadAccountData(this.principal);
    });
    
    let accountElm = document.getElementById("account");
    let savingsAccountElm = document.getElementById("savings_account");

    accountElm.addEventListener('click', () => {
      accountElm.classList.add('active');
      savingsAccountElm.classList.remove('active');
      this.loadAccountData(this.principal)
    })

    savingsAccountElm.addEventListener('click', () => {
      savingsAccountElm.classList.add('active');
      accountElm.classList.remove('active');
      this.loadSavingsAccountData(this.principal);
    })

  }

  loadAccountData(id : number) {
    this.accountsService.getAccount(id).subscribe(data => {
        // console.log(data);
        this.account = data;
        document.getElementById("current_balance").innerText = this.account.accountBalance.toString();

        this.transactionService.getTransactions(this.account.accountNum).subscribe( data => {
          this.displayed_transactions = data;
        })
      }, err => console.log(err))
  }

  loadSavingsAccountData(id : number) {
    this.accountsService.getSavingsAccount(id).subscribe( data => {
      // console.log(data)
      this.savings_account = data;
      document.getElementById("current_balance").innerText = this.savings_account.accountBalance.toString();

      this.transactionService.getTransactions(this.savings_account.accountNum).subscribe( data => {
        this.displayed_transactions = data;
      })
    }, err => console.log(err))
  }

}
