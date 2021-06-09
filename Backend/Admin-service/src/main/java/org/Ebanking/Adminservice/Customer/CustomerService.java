package org.Ebanking.Adminservice.Customer;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/customers")
    public List<Customer> getCustomerList(@RequestHeader("Authorization") String jwt);

    @RequestMapping(method = RequestMethod.GET, value = "/api/customers/{id}")
    public Customer getCustomerById(@RequestHeader("Authorization") String jwt, @PathVariable(name = "id") Long customerId);

    @RequestMapping(method = RequestMethod.POST, value = "/api/customers")
    public Customer addCustomer(@RequestHeader("Authorization") String jwt, Customer customer);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/customers/{id}")
    public void editCustomerById(@RequestHeader("Authorization") String jwt,@PathVariable(name = "id") Long id, Customer customer);

    @RequestMapping(value = "/api/customersByAgencyId/{agencyId}", method = RequestMethod.GET)
    public List<Customer> getCustomerByAgencyId(@RequestHeader("Authorization") String jwt, @PathVariable(name = "agencyId") Long agencyId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/officers/{id}")
    public void deleteCustomerById(@RequestHeader("Authorization") String jwt,@PathVariable(name = "id") Long customerId);


}
