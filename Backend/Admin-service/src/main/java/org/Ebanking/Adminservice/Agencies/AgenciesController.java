package org.Ebanking.Adminservice.Agencies;


import org.Ebanking.Adminservice.Customer.CustomerService;
import org.Ebanking.Adminservice.Officers.Officer;
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
import java.util.stream.Collectors;


@Controller
public class AgenciesController {
    @Autowired
    AgencyService agencyService;
    @Autowired
    OfficerService officerService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CurrentJWT currentJWT;

    Logger logger =  LoggerFactory.getLogger( AgenciesController.class);

    @RequestMapping(value = "/list_agences", method = RequestMethod.GET)
    public String getAgenciesList(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
        logger.info ( "Getting list-agencies.html .." );
        List<Agency> agencies = agencyService.getAgenciesList ( currentJWT.getJwt ( request ) );
        Integer customersNumber = customerService.getCustomerList ( currentJWT.getJwt ( request ) ).size ();
        Integer officersNumber = officerService.getOfficersList ( currentJWT.getJwt ( request ) ).size ();
        response.addHeader ( "agencies", String.valueOf ( agencies.size () ) );
        response.addHeader ( "customers", String.valueOf ( customersNumber ) );
        response.addHeader ( "officers", String.valueOf ( officersNumber ) );
        model.addAttribute ( "agencies", agencies );
        return "list-agencies";
    }

    @RequestMapping(value = "/agences/{id}", method = RequestMethod.GET)
    public String getAgencyById(@PathVariable(name = "id") Long id, HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        logger.info ( "Getting vis-agence.html .." );
        Agency agency = agencyService.getAgencyById ( currentJWT.getJwt ( request ),id );
        List<Officer> officers = officerService.getOfficersByAgencyId (currentJWT.getJwt ( request ),agency.getId ());
        List<Officer> officerslist = officers.stream ().filter ( c-> c.getRole ().contains ( "Dir" ) ).collect ( Collectors.toList () );
        Officer officer;
        if (!officerslist.isEmpty ()){
            officer = officerslist.get ( officerslist.size ()-1 );
        }else {
            officer = null;
        }
        model.addAttribute ( "agency", agency );
        model.addAttribute ( "director", officer );
        return "vis-agence";
    }
    @RequestMapping(value = "/modifierAgence/{id}", method = RequestMethod.GET)
    public String EditAgency(@PathVariable(name = "id") Long id, ModelMap model, HttpServletRequest request) {
        logger.info ( "Getting mod-agence.html .." );
        Agency agency = agencyService.getAgencyById (currentJWT.getJwt ( request ), id );
        List<Officer> officers = officerService.getOfficersByAgencyId (currentJWT.getJwt ( request ),agency.getId ());
        List<Officer> officerslist = officers.stream ().filter ( c-> c.getRole ().contains ( "Dir" ) ).collect ( Collectors.toList () );
        Officer officer;
        if (!officerslist.isEmpty ()){
            officer = officerslist.get ( officerslist.size ()-1 );
        }else {
            officer = null;
        }
        model.addAttribute ( "agency", agency );
        model.addAttribute ( "officersList", officers );
        model.addAttribute ( "director", officer );
        return "mod-agence";
    }
    @RequestMapping(value = "/updateAgence/{id}", method = RequestMethod.POST)
    public String EditAgency(@PathVariable(name = "id") Long id,@ModelAttribute Agency agency, HttpServletRequest request,ModelMap model) {
        logger.info ( "editing an agency .." );
        Agency agencyBefore = agencyService.getAgencyById ( currentJWT.getJwt ( request ),id );
        Agency agencyUpdated = agencyService.editAgencyById ( currentJWT.getJwt ( request ),id, agency );
        if (agencyUpdated.getAgencyChefId ()!=agencyBefore.getAgencyChefId ()){
            Officer directeur = officerService.getOfficerById ( currentJWT.getJwt ( request ),agencyUpdated.getAgencyChefId () );
            directeur.setRole ( "Directeur" );
            Officer Ex_directeur = officerService.getOfficerById ( currentJWT.getJwt ( request ),agencyBefore.getAgencyChefId () );
            Ex_directeur.setRole ( "officer" );
            officerService.editOfficerById ( currentJWT.getJwt ( request ), agencyUpdated.getAgencyChefId (),directeur );
            officerService.editOfficerById ( currentJWT.getJwt ( request ), agencyBefore.getAgencyChefId (),Ex_directeur );
        }
        model.addAttribute ( "agency", agencyUpdated );
        return "redirect:/agences/"+id;
    }


    @RequestMapping(value = "/ajouterAgence", method = RequestMethod.GET)
    public String addAgency( ModelMap model) {
        logger.info ( "Getting add-agence.html .." );
        model.addAttribute ( "agency", new Agency (  ) );
        return "add-agence";
    }

    @RequestMapping(value = "/enregistrerAgence", method = RequestMethod.POST)
    public String saveAgency(@ModelAttribute Agency agency, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        logger.info ( "Saving an new agency .." );
        agency.setFax ("+"+agency.getFax ());
        Agency agencyAdded = agencyService.addAgency ( currentJWT.getJwt ( request ), agency);
        model.addAttribute ( "agency", agencyAdded );
        return "vis-agence";
    }

    @RequestMapping(value = "/supprimerAgence/{id}", method = RequestMethod.GET)
    public String deleteAgency(@PathVariable( name = "id") Long id, HttpServletRequest request, HttpServletResponse response) {
        logger.info ( "Deleting an agency  .." );
        agencyService.deleteAgencyById ( currentJWT.getJwt ( request ),id );
        return "redirect:/list_agences";
    }
}
