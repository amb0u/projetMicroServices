package org.Ebanking.agencyservice.classes;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient (name = "OFFICER-SERVICE")
public interface OfficerService {

        @RequestMapping(value = "/api/officerAgencyIdByEmail/{email}", method = RequestMethod.GET)
        public Long getOfficersAgencyIdByEmail(@RequestHeader("Authorization") String jwt, @PathVariable(name = "email") String username);

}
