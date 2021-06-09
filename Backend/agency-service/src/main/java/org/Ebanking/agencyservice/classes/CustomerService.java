package org.Ebanking.agencyservice.classes;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient (name = "CUSTOMER-SERVICE")
public interface CustomerService {

    @RequestMapping(value = "/api/CustomerAgencyIdByEmail/{email}", method = RequestMethod.GET)
    public Long getCustomerAgencyIdByEmail(@RequestHeader("Authorization") String jwt, @PathVariable(name = "email") String username);

}
