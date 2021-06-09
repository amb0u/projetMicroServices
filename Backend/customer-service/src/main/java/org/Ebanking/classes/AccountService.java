package org.Ebanking.classes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient (name = "ACCOUNT-SERVICE")
public interface AccountService {
        @GetMapping("/api/customer/currentAccounts/{clientId}")
        public currentAccount findCurrentAccountByCustomerId(@PathVariable(name="clientId") Long clientId);

        @DeleteMapping("/api/customer/currentAccounts/{clientId}")
        public void deleteCurrentAccountsByClientId(@RequestHeader( "Authorization" ) String jwt, @PathVariable(name= "clientId") Long clientId);

        @GetMapping("/api/customer/savingsAccounts/{clientId}")
        public savingsAccount findSavingsAccountByCustomerId(@PathVariable(name="clientId") Long clientId);

        @DeleteMapping("/api/customer/savingsAccounts/{id}")
        public void deleteSavingsAccountsByClientId(@RequestHeader ( "Authorization" ) String jwt,@PathVariable(name= "id") Long clientId);
}
