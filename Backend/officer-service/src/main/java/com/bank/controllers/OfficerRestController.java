package com.bank.controllers;
import com.bank.entities.Officer;
import com.bank.repositories.OfficerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class OfficerRestController {
    private OfficerRepository officerRepository;

    @RequestMapping(value = "/officers" , method = RequestMethod.GET)
    public List<Officer> getOfficers() {
        return officerRepository.findAll ();
    }

    @RequestMapping(value = "/officers/{id}" , method = RequestMethod.GET)
    public Officer getOfficersById(@PathVariable( name = "id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        Officer officer = officerRepository.findById(id).get();



        if(authentication.getAuthorities ().toString ().equals ("[ADMIN]" )){
            return officerRepository.findById ( id ).get ();
        }

        else if (authentication.getAuthorities ().toString ().equals ("[OFFICER]" )) {
            if (authentication.getName ().equals ( officer.getEmail () )) {
                return officerRepository.findById ( id ).get ();
            }
        }

        return null;
    }
    @RequestMapping(value = "/officerByEmail/{email}" , method = RequestMethod.GET)
    public Officer getOfficersByEmail(@PathVariable( name = "email") String email) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        Officer officer = officerRepository.findOfficerByEmail(email);

        if(authentication.getAuthorities ().toString ().equals ("[ADMIN]" )){
            return officer;
        }

        else if (authentication.getAuthorities ().toString ().equals ("[OFFICER]" )) {
            if (authentication.getName ().equals ( officer.getEmail () )) {
                return officer;
            }
        }

        return null;
    }

    @RequestMapping(value = "/officerByRole/{role}", method = RequestMethod.GET)
    public Collection<Officer> getOfficersByRole(@PathVariable(name = "role") String role) {
        return officerRepository.findOfficerByRole(role);
    }
    @RequestMapping(value = "/getMyId" , method = RequestMethod.GET)
    public Long getOfficerId( HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" )){
            Officer officer = officerRepository.findOfficerByEmail (authentication.getName ());
            return officer.getId ();
        }
        response.setStatus ( 403 );
        return null;

    }

    @RequestMapping(value = "/officerByAgencyId/{agencyId}", method = RequestMethod.GET)
    public Collection<Officer> getOfficersByAgencyId(@PathVariable(name = "agencyId") Long agencyId) {
        return officerRepository.findOfficerByAgencyId(agencyId);
    }

    @RequestMapping(value = "/officers" , method = RequestMethod.POST)
    public Officer saveOfficer(@RequestBody Officer officer){
        return officerRepository.save(officer);
    }

    @PutMapping("/officers/{id}")
    public ResponseEntity<Object> updateOfficer(@RequestBody Officer officer, @PathVariable long id) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        Optional<Officer> officerOptional = officerRepository.findById(id);

        if (!officerOptional.isPresent()) {
            return ResponseEntity.notFound ().build ();
        }
        else if(authentication.getAuthorities ().toString ().equals ("[ADMIN]" )){
            officer.setId ( id );
            officerRepository.save ( officer );
            return ResponseEntity.accepted().build();
        }
        else  if(authentication.getName ().equals ( officerOptional.get ().getEmail () )) {
            officer.setId ( id );
            officer.setAgencyId ( officerOptional.get ().getAgencyId ());
            officerRepository.save ( officer );
            return ResponseEntity.accepted().build();
        }
       return ResponseEntity.badRequest ().build ();
    }

    @DeleteMapping(value = "/officers/{id}")
    public void deleteOfficerById (@PathVariable(name = "id") long id ){
        officerRepository.deleteById(id);
    }


    // Need it in Customer Service
    @RequestMapping(value = "/officerAgencyIdByEmail/{email}", method = RequestMethod.GET )
    public Long getOfficersAgencyIdByEmail(@PathVariable(name = "email") String email, HttpServletResponse response ) {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();


        if ( authentication.getAuthorities ().toString ().equals ( "[OFFICER]" )) {
                response.setStatus(200);
                return officerRepository.findOfficerByEmail ( email ).getAgencyId ();
        }
        response.setStatus(403);
        return null;
    }
}

