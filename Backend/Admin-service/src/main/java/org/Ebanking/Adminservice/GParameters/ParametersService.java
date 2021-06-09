package org.Ebanking.Adminservice.GParameters;


import org.Ebanking.Adminservice.GParameters.contracts.ContratType;
import org.Ebanking.Adminservice.GParameters.currency.Currency;
import org.Ebanking.Adminservice.GParameters.holidays.HolidayDay;
import org.Ebanking.Adminservice.GParameters.roles.Role;
import org.Ebanking.Adminservice.GParameters.status.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "GLOBALPARAMETERS-SERVICE")
public interface ParametersService {

    @RequestMapping(method = RequestMethod.GET, value = "/api/status")
    public List<Status> getStatus(@RequestHeader("Authorization") String jwt);

    @RequestMapping(method = RequestMethod.GET, value = "/api/status")
    public Status addStatus(@RequestHeader("Authorization") String jwt, Status newStatus);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/status/{id}")
    public void deleteStatus(@RequestHeader("Authorization") String jwt, @PathVariable(name = "id") Long statusId);


    @RequestMapping(method = RequestMethod.GET, value = "/api/roles")
    public List<Role> getRoles(@RequestHeader("Authorization") String jwt);

    @RequestMapping(method = RequestMethod.GET, value = "/api/roles")
    public Role addRole(@RequestHeader("Authorization") String jwt, Role newRole);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/roles/{id}")
    public void deleteRole(@RequestHeader("Authorization") String jwt, @PathVariable(name = "id") Long roleId);


    @RequestMapping(method = RequestMethod.GET, value = "/api/holidays")
    public List<HolidayDay> getHolidays(@RequestHeader("Authorization") String jwt);

    @RequestMapping(method = RequestMethod.GET, value = "/api/holidays")
    public HolidayDay addHoliday(@RequestHeader("Authorization") String jwt, HolidayDay newHoliday);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/holidays/{id}")
    public void deleteHoliday(@RequestHeader("Authorization") String jwt, @PathVariable(name = "id") Long holidayId);


    @RequestMapping(method = RequestMethod.GET, value = "/api/currency")
    public Optional<Currency> getCurrency(@RequestHeader("Authorization") String jwt);

    @RequestMapping(method = RequestMethod.GET, value = "/api/currency")
    public Currency addCurrency(@RequestHeader("Authorization") String jwt, Currency newCurrency);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/currency")
    public void editCurrency(@RequestHeader("Authorization") String jwt, Currency newCurrency);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/currency")
    public void deleteCurrency(@RequestHeader("Authorization") String jwt);



    @RequestMapping(method = RequestMethod.GET, value = "/api/contrats")
    public List<ContratType> getContratTypes(@RequestHeader("Authorization") String jwt);

    @RequestMapping(method = RequestMethod.GET, value = "/api/contrats")
    public ContratType addContratType(@RequestHeader("Authorization") String jwt, ContratType newContract);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/contrats/{id}")
    public void deleteContratType(@RequestHeader("Authorization") String jwt, @PathVariable(name = "id") Long contractId);


}
