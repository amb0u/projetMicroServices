package com.Ebanking.controllers;

import com.Ebanking.Repositories.DemandeRepository;
import com.Ebanking.classes.CustomerService;
import com.Ebanking.classes.OfficerService;
import com.Ebanking.entities.Demande;
import com.Ebanking.entities.Dstatus;
import com.Ebanking.service.CurrentJWT;
import lombok.AllArgsConstructor;
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
public class DemandeRestController {
    private DemandeRepository demandeRepository;
    private CurrentJWT currentJWT;
    private CustomerService customerService;
    private OfficerService officerService;

    @RequestMapping(value = "/demandes" , method = RequestMethod.GET)
    public List<Demande> getDemandes(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owners
            Long officerAgencyId = officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () );
            List <Demande> demandes = demandeRepository.findDemandeByAgencyId(officerAgencyId);
            if( demandes.size()!= 0) {
                response.setStatus(200);
                return demandes;
            }
        }
        response.setStatus ( 403 );
        return null;
    }
    @RequestMapping(value = "/customer/demandes/{clientId}" , method = RequestMethod.GET)
    public List <Demande> getDemandeByClientId(@PathVariable(name = "clientId") Long clientId, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the owner to get his requests infos.
            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == clientId){
                response.setStatus ( 200 );
                return demandeRepository.findDemandeByClientId(clientId);
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the requests
             Long officerAgencyId = officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName ());
             response.setStatus ( 200 );
             return demandeRepository.findDemandeByClientIdAndAgencyId(clientId,officerAgencyId);
        }
        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/demandes/{id}" , method = RequestMethod.GET)
    public Demande getDemandesById(@PathVariable( name = "id") Long id,  HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        Demande demande = demandeRepository.findById(id).get();
        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the owner to get his request infos.
            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == demande.getClientId()){
                response.setStatus ( 200 );
                return demande;
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the request
           Long officerAgencyId = officerService.getOfficersAgencyIdByEmail(currentJWT.getJWT(request), authentication.getName());
            if (officerAgencyId == demande.getAgencyId()) {
                response.setStatus(200);
                return demandeRepository.findDemandeByIdAndAgencyId( id,officerAgencyId);
            }
        }
        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/demandes/ByStatus/{status}" , method = RequestMethod.GET)
    public List <Demande> getDemandeByStatus(@PathVariable(name = "status") Dstatus status , HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the request
            Long officerAgencyId = officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () );
            List<Demande> demandes = demandeRepository.findDemandeByStatusAndAgencyId(status,officerAgencyId);
            if( demandes.size()!= 0) {
                response.setStatus(200);
                return demandes;
            }
        }
        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/demandes" , method = RequestMethod.POST)
    public Demande saveDemande(@RequestBody Demande demande, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
       Long clientId = customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName ());
        Long clientAgencyId = customerService.getCustomerAgencyIdByEmail(currentJWT.getJWT ( request ), authentication.getName());

        Demande dm = new Demande( new Date( ),Dstatus.enCours,demande.getMessage(),clientId,demande.getType(),clientAgencyId);
        response.setStatus(200);
        return demandeRepository.save(dm);
    }

    @RequestMapping(value = "/demandes/traite/{id}" , method = RequestMethod.PUT)
    public  void ChangeDemandeStatus(@PathVariable(name = "id") Long id, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        Demande demande = demandeRepository.findById(id).get();
        if (demandeRepository.existsById ( id )) {
            if (authentication.getAuthorities().toString().equals("[OFFICER]")) { // Officer must have the same agencyId as the owner of the request that he want to edit.
                if (officerService.getOfficersAgencyIdByEmail(currentJWT.getJWT(request), authentication.getName()) == demande.getAgencyId()) {
                    demandeRepository.editStatus(Dstatus.traite, id);
                    response.setStatus(200);
                }
            }
        }
        else response.setStatus ( 403 );
    }

    @DeleteMapping(value = "/demandes/{id}")
    public void deleteDemandeById (@PathVariable(name = "id") long id, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        Demande demande = demandeRepository.findById(id).get();
        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the owner to delete his request.

            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == demande.getClientId()){
                demandeRepository.deleteById(id);
                response.setStatus ( 200 );
            }
        }
       else  if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the request that he wants to delete.

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == demande.getAgencyId()){
                demandeRepository.deleteById(id);
                response.setStatus ( 200 );
            }
        }
        else response.setStatus ( 403 );
    }

    @RequestMapping(value = "/customer/demandes/{clientId}" , method = RequestMethod.DELETE)
    public void deleteDemandeByClientId(@PathVariable(name = "clientId") Long clientId,  HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the owner to get his request infos.

            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == clientId){
                demandeRepository.deleteDemandeByClientId ( clientId );
                response.setStatus ( 200 );
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the request
            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                demandeRepository.deleteDemandeByClientId ( clientId );
                response.setStatus ( 200 );
            }
        }
        else response.setStatus ( 403 );
    }

}
