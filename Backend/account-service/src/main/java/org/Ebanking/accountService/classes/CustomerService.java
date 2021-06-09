package org.Ebanking.accountService.classes;

import org.Ebanking.accountService.entities.CurrentAccount;
import org.Ebanking.accountService.entities.SavingsAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerService {
    @RequestMapping(method = RequestMethod.POST, value = "/api/customer/currentAccount", consumes = "application/json")
    public void addCustomersCurrentAccount(@RequestHeader("Authorization") String jwt,CurrentAccount newCurrentAccount );

    @RequestMapping(method = RequestMethod.POST, value = "/api/customer/savingsAccount", consumes = "application/json")
    public void addCustomersSavingsAccount(@RequestHeader("Authorization") String jwt,SavingsAccount savingsAccount);

    @RequestMapping(value = "/api/CustomerIdByEmail/{email}", method = RequestMethod.GET)
    public Long getCustomerIdByEmail(@RequestHeader("Authorization") String jwt, @PathVariable(name = "email") String username);


    @RequestMapping(value = "/api/CustomerAgencyIdById/{id}", method = RequestMethod.GET)
    public Long getCustomerAgencyIdById(@RequestHeader("Authorization") String jwt, @PathVariable( name = "id") Long id);

}

