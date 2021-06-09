package org.Ebanking.Adminservice.Officers;


import org.Ebanking.Adminservice.Agencies.Agency;
import org.Ebanking.Adminservice.Agencies.AgencyService;
import org.Ebanking.Adminservice.Customer.CustomerService;
import org.Ebanking.Adminservice.GParameters.roles.RoleController;
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
public class OfficerController {
    @Autowired
    OfficerService officerService;
    @Autowired
    AgencyService agencyService;
    @Autowired
    RoleController roleController;
    @Autowired
    CustomerService customerService;
    @Autowired
    CurrentJWT currentJWT;

    Logger logger =  LoggerFactory.getLogger( OfficerController.class);

    @RequestMapping(value = "/list_agents", method = RequestMethod.GET)
    public String getOfficersList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        logger.info ( "Getting list-officers.html .." );
        List<Officer> officers = officerService.getOfficersList (( currentJWT.getJwt ( request ) ));
        Integer agenciesNumber = agencyService.getAgenciesList ( currentJWT.getJwt ( request ) ).size ();
        Integer customersNumber = customerService.getCustomerList ( currentJWT.getJwt ( request ) ).size ();
        response.addHeader ( "agencies", String.valueOf ( agenciesNumber ) );
        response.addHeader ( "customers", String.valueOf ( customersNumber ) );
        response.addHeader ( "officers", String.valueOf ( officers.size () ) );
        model.addAttribute ( "officers", officers );
        return "list-officers";
    }

    @RequestMapping(value = "/agents/{id}", method = RequestMethod.GET)
    public String getOfficerById(@PathVariable(name = "id") Long id, HttpServletRequest request, ModelMap model) {
        logger.info ( "Getting vis-officer.html .." );
        Officer officer = officerService.getOfficerById ( currentJWT.getJwt ( request ),id );
        officer.setBirthDay ( officer.getBirthDay ().substring ( 0,10 ));
        Agency agency = agencyService.getAgencyById ( currentJWT.getJwt ( request ),officer.getAgencyId () );
        model.addAttribute ( "officer", officer );
        model.addAttribute ( "agencyName", agency.getName () );
        return "vis-officer";
    }
    @RequestMapping(value = "/modifierAgent/{id}", method = RequestMethod.GET)
    public String EditOfficer(@PathVariable(name = "id") Long id, HttpServletRequest request,ModelMap model) {
        logger.info ( "Getting mod-officer.html .." );
        Officer officer = officerService.getOfficerById ( currentJWT.getJwt ( request ),id );
        officer.setBirthDay ( officer.getBirthDay ().substring ( 0,10 ));
        Agency agency = agencyService.getAgencyById ( currentJWT.getJwt ( request ),officer.getAgencyId () );
        List<Agency> agencies = agencyService.getAgenciesList ( currentJWT.getJwt ( request ) );
        model.addAttribute ( "officer", officer );
        model.addAttribute ( "OfficersAgencyName", agency);
        model.addAttribute ( "agencies", agencies);
        model.addAttribute ( "roles", roleController.getRolesList ( request ) );
        return "mod-officer";
    }
    @RequestMapping(value = "/updateAgent/{id}", method = RequestMethod.POST)
    public String EditOfficer(@PathVariable(name = "id") Long id,@ModelAttribute Officer officer, HttpServletRequest request) {
        logger.info ( "editing an officer .." );
        officerService.editOfficerById ( currentJWT.getJwt ( request ),id,officer );
        return "redirect:/agents/"+id;
    }

    @RequestMapping(value = "/ajouterAgent", method = RequestMethod.GET)
    public String addOfficer( ModelMap model, HttpServletRequest request) {
        logger.info ( "Getting add-officer.html .." );
        List<Agency> agencies = agencyService.getAgenciesList ( currentJWT.getJwt ( request ) );
        model.addAttribute ( "officer", new Officer (  ) );
        model.addAttribute ( "agencies", agencies );
        model.addAttribute ( "roles",  roleController.getRolesList ( request ));
        return "add-officer";
    }

    @RequestMapping(value = "/enregistrerAgent", method = RequestMethod.POST)
    public String saveOfficer(@ModelAttribute Officer officer, HttpServletRequest request, ModelMap model) {
        logger.info ( "Adding new Officer .." );
        Officer addedOfficer = officerService.addOfficer ( currentJWT.getJwt ( request ),officer );
        Agency agency = agencyService.getAgencyById ( currentJWT.getJwt ( request ),officer.getAgencyId () );
        model.addAttribute ( "officer", addedOfficer );
        model.addAttribute ( "agencyName", agency.getName () );
        return "vis-officer";
    }

    @RequestMapping(value = "/supprimerAgent/{id}", method = RequestMethod.GET)
    public String deleteOfficer(@PathVariable( name = "id") Long id, HttpServletRequest request) {
        logger.info ( "Deleting an Officer .." );
        officerService.deleteOfficerById ( currentJWT.getJwt ( request ),id );
        return "redirect:/list_agents";
    }
}
