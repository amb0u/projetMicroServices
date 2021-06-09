package org.Ebanking.transactionservice.classes;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountService {

    @RequestMapping(value = "/api/currentAccounts/Balance/{accountNum}", method = RequestMethod.GET)
    public Long getCurrentAccountBalanceByAccountNum(@RequestHeader("Authorization") String jwt, @PathVariable(name = "accountNum") Long accountNum);


}

