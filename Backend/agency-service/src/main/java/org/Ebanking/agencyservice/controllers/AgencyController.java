package org.Ebanking.agencyservice.controllers;

import lombok.AllArgsConstructor;
import org.Ebanking.agencyservice.classes.CustomerService;
import org.Ebanking.agencyservice.classes.OfficerService;
import org.Ebanking.agencyservice.entities.Agency;
import org.Ebanking.agencyservice.repositories.AgencyRepository;
import org.Ebanking.agencyservice.service.CurrentJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class AgencyController {

    private AgencyRepository agencyRepository;
    private CurrentJWT currentJWT;
    private CustomerService customerService;
    private OfficerService officerService;

        @RequestMapping(value = "/agencies" , method = RequestMethod.GET)
        public List<Agency> getAgencies() {
            return agencyRepository.findAll ();
        }


        @RequestMapping(value = "/agencies/{id}" , method = RequestMethod.GET)
        public Agency getAgencyById(@PathVariable( name = "id") Long id, HttpServletRequest request, HttpServletResponse response) {
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
            Agency agence = agencyRepository.findById(id).get();
            if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be apart of this agency to get its infos

                if ( customerService.getCustomerAgencyIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == agence.getId()){
                    response.setStatus ( 200 );
                    return agencyRepository.findById(id).get();
                }
            }
            else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the request that he wants to delete.
                if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == agence.getId()){
                    response.setStatus ( 200 );
                    return agencyRepository.findById(id).get();
                }
            }
            else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" ) ) {
                response.setStatus ( 200 );
                return agencyRepository.findById(id).get();
            }

            response.setStatus ( 403 );
            return null;
        }

        @RequestMapping(value = "/agencies" , method = RequestMethod.POST)
        public Agency saveAgency(@RequestBody Agency agency){
            Agency newAgence = new Agency ( null, agency.getName (), agency.getAddress (), agency.getPhoneNumber (),agency.getEmail (), agency.getFax (),new Date (  ),null);
            return agencyRepository.save(newAgence);
        }


        @RequestMapping(value = "/agencies/{id}" , method = RequestMethod.PUT)
        public  Agency editAgencyById(@PathVariable(name = "id") Long id, @RequestBody Agency agency){
            if (agencyRepository.existsById ( id )){
                agency.setId ( id );
                agency.setCreationDate ( agencyRepository.findById ( id ).get ().getCreationDate () );
                return  agencyRepository.save ( agency );
            }
            return null;
        }


        @DeleteMapping(value = "/agencies/{id}")
        public void deleteAgencyById (@PathVariable(name = "id") Long id ){
            agencyRepository.deleteById(id);
        }

}
