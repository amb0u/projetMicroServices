package org.Ebanking.Adminservice.Officers;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "OFFICER-SERVICE")
public interface OfficerService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/officers")
    public List<Officer> getOfficersList(@RequestHeader("Authorization") String jwt);

    @RequestMapping(method = RequestMethod.GET, value = "/api/officers/{id}")
    public Officer getOfficerById(@RequestHeader("Authorization") String jwt, @PathVariable(name = "id") Long officeId);

    @RequestMapping(method = RequestMethod.POST, value = "/api/officers")
    public Officer addOfficer(@RequestHeader("Authorization") String jwt, Officer officer);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/officers/{id}")
    public void editOfficerById(@RequestHeader("Authorization") String jwt,@PathVariable(name = "id") Long id, Officer officer);

    @RequestMapping(value = "/api/officerByAgencyId/{agencyId}", method = RequestMethod.GET)
    public List<Officer> getOfficersByAgencyId(@RequestHeader("Authorization") String jwt, @PathVariable(name = "agencyId") Long agencyId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/officers/{id}")
    public void deleteOfficerById(@RequestHeader("Authorization") String jwt,@PathVariable(name = "id") Long officerId);


}
