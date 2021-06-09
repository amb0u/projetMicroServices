package org.Ebanking.Controllers;

import lombok.AllArgsConstructor;
import org.Ebanking.classes.*;
import org.Ebanking.entities.Customer;
import org.Ebanking.repositories.CustomerRepository;
import org.Ebanking.service.CurrentJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class CustomerRestController {

    private  CustomerRepository customerRepository;
    private AccountService accountService;
    private OfficerService officerService;
    private CurrentJWT currentJWT;


    @RequestMapping(value = "/customers" , method = RequestMethod.GET)
    public Collection<Customer> getCustomers(HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )) {  // Admin has the authority to get all the customers
            return customerRepository.findAll ();
        }

        response.setStatus ( 403 );
        return null;
    }


    @RequestMapping(value = "/customers/{id}" , method = RequestMethod.GET)
    public Customer getCustomersById(@PathVariable( name = "id") Long id, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) { // Admin has the authority to get any customer
                return customerRepository.findById ( id ).get ();
        }

        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the customer that he want to get.
                Customer customer = customerRepository.findById ( id ).get ();

                if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customer.getAgencyId ()){
                        return customerRepository.findById ( id ).get ();
                }
        }

        else if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the same customer to get his own information.
            Customer customer = customerRepository.findById ( id ).get ();

            if (customer.getEmail ().equals ( authentication.getName () )){
                return customerRepository.findById ( id ).get ();
            }
        }

        response.setStatus ( 403 );
        return null;

    }

    @RequestMapping(value = "/getMyId" , method = RequestMethod.GET)
    public Long getCustomerId( HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

         if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){
            Customer customer = customerRepository.findCustomerByEmail(authentication.getName ());
                return customer.getId ();
        }
        response.setStatus ( 403 );
        return null;

    }


    @RequestMapping(value = "/customerByEmail/{email}" , method = RequestMethod.GET)
    public Customer getCustomersByEmail(@PathVariable( name = "email") String email, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) { // Admin has the authority to get any customer
            return customerRepository.findCustomerByEmail(email);
        }

        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the customer that he want to get.
            Customer customer = customerRepository.findCustomerByEmail(email);

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customer.getAgencyId ()){
                return customer;
            }
        }

        else if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the same customer to get his own information.
            Customer customer = customerRepository.findCustomerByEmail(email);

            if (customer.getEmail ().equals ( authentication.getName () )){
                return customer;
            }
        }

        response.setStatus ( 403 );
        return null;

    }

    @RequestMapping(value = "/customersByCin/{CIN}" , method = RequestMethod.GET)
    public  Collection<Customer> getCustomersByCin(@PathVariable(name = "CIN") String cin, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) { // The filter will be applied to the whole list of customers.
            return customerRepository.findCustomerByCinContains(cin);
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // The filter will be applied to the list of customers that has the same agencyId as the Officer who called this endPoint.
            Long officerAgencyId = officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () );
            return customerRepository.findCustomerByCinContainsAndAgencyId ( cin, officerAgencyId );
        }
        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/customersByStatus/{Status}" , method = RequestMethod.GET)
    public Collection<Customer> getCustomersByStatus(@PathVariable(name = "Status")  String Status, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) { // The filter will be applied to the whole list of customers.
            return customerRepository.findCustomerByStatus (Status );
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // The filter will be applied to the list of customers that has the same agencyId as the Officer who called this endPoint.
            Long officerAgencyId = officerService.getOfficersAgencyIdByEmail ( currentJWT.getJWT ( request ), authentication.getName () );
            return customerRepository.findCustomerByStatusAndAgencyId ( Status, officerAgencyId );
        }
        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/customersByContract/{Contract}" , method = RequestMethod.GET)
    public Collection<Customer> getCustomersByContract(@PathVariable(name = "Contract") String Contract, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) { // The filter will be applied to the whole list of customers.
            return customerRepository.findCustomerByContract ( Contract );
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // The filter will be applied to the list of customers that has the same agencyId as the Officer who called this endPoint.
            Long officerAgencyId = officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ),  authentication.getName () );
            return customerRepository.findCustomerByContractAndAgencyId (  Contract, officerAgencyId );
        }
        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/customersByAgency/{agencyId}" , method = RequestMethod.GET)
    public Collection<Customer> getCustomersByAgency(@PathVariable(name = "agencyId") Long agencyId, HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) {
            return customerRepository.findCustomerByAgencyId ( agencyId );
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) {
            Long officerAgencyId = officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ),  authentication.getName ());

                if (officerAgencyId == agencyId) {
                    return customerRepository.findCustomerByAgencyId ( agencyId );
                }
        }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/customers" , method = RequestMethod.POST)
    public Customer saveAccount(@RequestBody addCustomerRequest createCustomer, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) { // The filter will be applied to the whole list of customers.
            Customer newCustomer = new Customer ( null, createCustomer.getFirstName (),createCustomer.getLastName () ,createCustomer.getEmail (),createCustomer.getAddress (), createCustomer.getBirthDay () , createCustomer.getCin (),createCustomer.getPhoneNumber (),createCustomer.getOperator (),createCustomer.getGender (),createCustomer.getStatus (),createCustomer.getContract (),new Date (),createCustomer.getAgencyId (),null, null);
            return customerRepository.save(newCustomer);
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" )) { // The filter will be applied to the list of customers that has the same agencyId as the Officer who called this endPoint.
                Long officerAgencyId = officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ),  authentication.getName () );
                Customer newCustomer = new Customer ( null, createCustomer.getFirstName (), createCustomer.getLastName (), createCustomer.getEmail (), createCustomer.getAddress (), createCustomer.getBirthDay (), createCustomer.getCin (), createCustomer.getPhoneNumber (), createCustomer.getOperator (), createCustomer.getGender (), createCustomer.getStatus (), createCustomer.getContract (), new Date (), officerAgencyId, null, null );
                return customerRepository.save ( newCustomer );
        }
        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/customer/currentAccount" , method = RequestMethod.POST) //TODO "/customer/currentAccount" We should verify the officer who called this
    public boolean addCustomersCurrentAccount(@RequestBody currentAccount newCurrentAccount, HttpServletResponse response ) {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ||  authentication.getAuthorities ().toString ().equals ( "[OFFICER]" )) {

                Long clientId = newCurrentAccount.getClientId ();
                Customer customer = customerRepository.findById ( clientId ).get ();
                if (customer.getCurrentAccountNum () == null) {
                    response.setStatus ( 200 );
                    customerRepository.AddCurrentAccountNumber ( newCurrentAccount.getAccountNum (), clientId );
                    return true;
                }
        }
        else   response.setStatus ( 403 );
        return false;

    }

    @RequestMapping(value = "/customer/savingsAccount" , method = RequestMethod.POST) //TODO We should verify the officer who called this
    public boolean addCustomersSavingsAccount(@RequestBody savingsAccount newSavingsAccount, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ||  authentication.getAuthorities ().toString ().equals ( "[OFFICER]" )) {

                Long clientId = newSavingsAccount.getClientId ();
                Customer customer = customerRepository.findById ( clientId ).get ();
                if (customer.getSavingsAccountNum () == null) {
                    response.setStatus ( 200 );
                    customerRepository.AddSavingsAccountNumber ( newSavingsAccount.getAccountNum (), clientId );
                    return true;
                }
        }
            response.setStatus ( 403 );
            return false;
    }


    @RequestMapping(value = "/customers/{id}" , method = RequestMethod.PUT)
    public boolean editCustomer(@PathVariable( name = "id") Long id,@RequestBody Customer newCustomer,HttpServletRequest request, HttpServletResponse response ) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (customerRepository.existsById ( id ) ){
            Customer customer = customerRepository.findById ( id ).get ();
            if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) {
                    newCustomer.setId ( id );
                    customerRepository.save ( newCustomer );
                    return true;
            }
            else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" )) {
                Long officerAgencyId = officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ),  authentication.getName () );
                if (officerAgencyId == customer.getAgencyId ()) {
                    newCustomer.setId ( id );
                    newCustomer.setAgencyId ( customer.getAgencyId () );
                    customerRepository.save ( newCustomer );
                    return true;
                }
            }
            else if (authentication.getName ().equals ( customer.getEmail () )) {
                    newCustomer.setId ( id );
                    newCustomer.setAgencyId ( customer.getAgencyId () );
                    customerRepository.save ( newCustomer );
                    return true;
            }
            else {
                response.setStatus ( 403 );
                return false;
            }
        }
        response.setStatus ( 203 );
        return false;
    }

    @RequestMapping(value = "/customers/{id}" , method = RequestMethod.DELETE)
    public void deleteCustomersById(@PathVariable( name = "id") Long id, HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) { // Admin have the authority to delete any customer it
            accountService.deleteCurrentAccountsByClientId (currentJWT.getJWT ( request ), id );
            accountService.deleteSavingsAccountsByClientId (currentJWT.getJWT ( request ), id );
            customerRepository.deleteById ( id );
            return;
        }

        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer have the authority to delete the customer of its agency.

            System.out.println ("loool");
            Customer customer = customerRepository.findById ( id ).get ();

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ),  authentication.getName () ) == customer.getAgencyId ()){
                System.out.println ("Haha");
                accountService.deleteCurrentAccountsByClientId (currentJWT.getJWT ( request ), id );
                accountService.deleteSavingsAccountsByClientId (currentJWT.getJWT ( request ), id );
                customerRepository.deleteById ( id );
                return;
            }
        }

        System.out.println ("No way !!");
        response.setStatus ( 403 );
    }


    // Need it in Account-service && Request-Service !
    @RequestMapping(value = "/CustomerIdByEmail/{email}", method = RequestMethod.GET)
    public Long getCustomerIdByEmail(@PathVariable(name = "email") String email, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )  ) {
            response.setStatus ( 200 );
            return customerRepository.findCustomerByEmail ( email ).getId ();
        }
        response.setStatus ( 403 );
        return null;
    }
    // Need it in Request-Service !
    @RequestMapping(value = "/CustomerAgencyIdById/{id}", method = RequestMethod.GET)
    public Long getCustomerAgencyIdById(@PathVariable(name = "id") Long id , HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" )  ) {
            response.setStatus ( 200 );
            Customer client = customerRepository.findById ( id ).get ();
            return client.getAgencyId();
        }
        response.setStatus ( 403 );
        return null;
    }
    // Need it in Agency-service
    @RequestMapping(value = "/CustomerAgencyIdByEmail/{email}", method = RequestMethod.GET )
    public Long getCustomerAgencyIdByEmail(@PathVariable(name = "email") String email ,HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {
            response.setStatus(200);
            return customerRepository.findCustomerByEmail(email).getAgencyId();
        }
        response.setStatus ( 403 );
        return null;
    }

    // Need it in Transaction-Service
    @RequestMapping(value = "/CustomerAccountNumByEmail/{email}", method = RequestMethod.GET)
    public Long getCustomerAccountNumByEmail(@PathVariable(name = "email") String username, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

            if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {
                    response.setStatus(200);
                    return customerRepository.findCustomerByEmail ( username ).getCurrentAccountNum ();
            }

            response.setStatus ( 403 );
            return null;
    }


    /** --------------------  Front-end need this  --------------------- */
    @RequestMapping(value = "/customersSizePerAgency", method = RequestMethod.GET)
    public Integer getCustomersSizePerAgency( HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if ( authentication.getAuthorities ().toString ().equals ( "[OFFICER]" )) {
            Long agencyId = officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () );
            response.setStatus(200);
            return customerRepository.findCustomerByAgencyId ( agencyId ).size ();
        }

        response.setStatus ( 403 );
        return null;
    }
}
