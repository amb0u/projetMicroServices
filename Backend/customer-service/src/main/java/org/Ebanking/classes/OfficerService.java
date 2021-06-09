package org.Ebanking.classes;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient (name = "OFFICER-SERVICE")
public interface OfficerService {

        @RequestMapping(value = "/api/officerAgencyIdByEmail/{email}", method = RequestMethod.GET)
        public Long getOfficersAgencyIdByEmail(@RequestHeader ( "Authorization" ) String jwt, @PathVariable( name = "email") String username);

}
