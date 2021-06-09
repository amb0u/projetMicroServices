package org.Ebanking.accountService.classes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;


@FeignClient(name = "transaction-service")
public interface TransactionService {

    @RequestMapping(value = "/api/account/recharges/{accountNum}" , method = RequestMethod.GET)
    public Collection<Recharge> getRechargeByAccountNum(@PathVariable(name = "accountNum") Long accountNum );

    @RequestMapping(value = "/api/account/virements/{accountNum}" , method = RequestMethod.GET)
    public Collection<Virement> getVirementByAccountNum(@PathVariable(name = "accountNum") Long accountNum);



}
