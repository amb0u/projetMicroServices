package org.Ebanking.Adminservice.Customer;


import org.Ebanking.Adminservice.Agencies.Agency;
import org.Ebanking.Adminservice.Agencies.AgencyService;
import org.Ebanking.Adminservice.GParameters.contracts.ContractController;
import org.Ebanking.Adminservice.GParameters.status.StatusController;
import org.Ebanking.Adminservice.Officers.OfficerService;
import org.Ebanking.Adminservice.global.CurrentJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ContractController contractController;
    @Autowired
    StatusController statusController;
    @Autowired
    AgencyService agencyService;
    @Autowired
    OfficerService officerService;
    @Autowired
    CurrentJWT currentJWT;

    Logger logger =  LoggerFactory.getLogger( CustomerController.class);

    @RequestMapping(value = "/list_clients", method = RequestMethod.GET)
    public String getCustomersList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        logger.info ( "Getting list-clients.html .." );
        List<Customer> customers = customerService.getCustomerList ( currentJWT.getJwt ( request ) );
        Integer agencies = agencyService.getAgenciesList ( currentJWT.getJwt ( request ) ).size ();
        Integer officersNumber = officerService.getOfficersList ( currentJWT.getJwt ( request ) ).size ();
        response.addHeader ( "agencies", String.valueOf ( agencies ) );
        response.addHeader ( "customers", String.valueOf ( customers.size ()) );
        response.addHeader ( "officers", String.valueOf ( officersNumber ) );
        model.addAttribute ( "customers", customers );
        return "list-customers";
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public String getCustomerById(@PathVariable(name = "id") Long id, HttpServletRequest request, ModelMap model) {
        logger.info ( "Getting vis-customer.html .." );
        Customer customer = customerService.getCustomerById ( currentJWT.getJwt ( request ),id );
        customer.setBirthDay ( customer.getBirthDay ().substring ( 0,10 ));
        Agency agency = agencyService.getAgencyById ( currentJWT.getJwt ( request ),customer.getAgencyId () );
        model.addAttribute ( "agencyName", agency.getName () );
        model.addAttribute ( "customer", customer );
        return "vis-customer";
    }
    @RequestMapping(value = "/modifierClient/{id}", method = RequestMethod.GET)
    public String EditCustomer(@PathVariable(name = "id") Long id, HttpServletRequest request,ModelMap model) {
        logger.info ( "Getting mod-customer.html .." );
        Customer customer = customerService.getCustomerById ( currentJWT.getJwt ( request ),id );
        customer.setBirthDay ( customer.getBirthDay ().substring ( 0,10 ));
        model.addAttribute ( "customer", customer );
        model.addAttribute ( "contracts", contractController.getContractsList ( request ) );
        model.addAttribute ( "agencies", agencyService.getAgenciesList ( currentJWT.getJwt ( request ) ));
        model.addAttribute ( "status", statusController.getStatusList ( request )  );
        return "mod-customer";
    }

    @RequestMapping(value = "/updateClient/{id}", method = RequestMethod.POST)
    public String updateCustomer(@PathVariable( name = "id") Long id,@ModelAttribute Customer customer, HttpServletRequest request) {
        logger.info ( "Adding new Customer .." );
        customerService.editCustomerById ( currentJWT.getJwt ( request ), id,customer);
        return "redirect:/clients/"+id;
    }

    @RequestMapping(value = "/ajouterClient", method = RequestMethod.GET)
    public String addCustomer(HttpServletRequest request, ModelMap model) {
        logger.info ( "Getting add-customer.html .." );
        model.addAttribute ( "customer", new Customer (  ) );
        model.addAttribute ( "contracts", contractController.getContractsList ( request ) );
        model.addAttribute ( "agencies", agencyService.getAgenciesList ( currentJWT.getJwt ( request ) ));
        return "add-customer";
    }

    @RequestMapping(value = "/enregistrerClient", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute Customer customer, HttpServletRequest request) {
        logger.info ( "Adding new Customer .." );
        customer.setStatus ( "Actif" );
        Customer newCustomer = customerService.addCustomer ( currentJWT.getJwt ( request ), customer);
        return "redirect:/clients/"+newCustomer.getId ();
    }

    @RequestMapping(value = "/supprimerClient/{id}", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable( name = "id") Long id, HttpServletRequest request) {
        logger.info ( "Deleting a Customer .." );
        customerService.deleteCustomerById ( currentJWT.getJwt ( request ), id );
        return "redirect:/list_clients";
    }
}
