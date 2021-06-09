package org.Ebanking.Adminservice.Agencies;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "AGENCY-SERVICE")
public interface AgencyService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/agencies")
    public List<Agency> getAgenciesList(@RequestHeader("Authorization") String jwt);

    @RequestMapping(method = RequestMethod.GET, value = "/api/agencies/{id}")
    public Agency getAgencyById(@RequestHeader("Authorization") String jwt,@PathVariable(name = "id") Long agencyId);

    @RequestMapping(method = RequestMethod.POST, value = "/api/agencies")
    public Agency addAgency(@RequestHeader("Authorization") String jwt, Agency agency);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/agencies/{id}")
    public Agency editAgencyById(@RequestHeader("Authorization") String jwt,@PathVariable(name = "id") Long id, Agency agency);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/agencies/{id}")
    public void deleteAgencyById(@RequestHeader("Authorization") String jwt,@PathVariable(name = "id") Long agencyId);

}
