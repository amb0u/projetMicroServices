package org.Ebanking.Adminservice.GParameters.holidays;

import org.Ebanking.Adminservice.GParameters.ParametersService;
import org.Ebanking.Adminservice.global.CurrentJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class HolidaysController {
    @Autowired
    ParametersService holidaysService;
    @Autowired
    CurrentJWT currentJWT;
    Logger logger =  LoggerFactory.getLogger( HolidaysController.class);

    public List<HolidayDay> getHolidaysList(HttpServletRequest request) {
        logger.info ( "Getting list-Holidays .." );
        return holidaysService.getHolidays ( currentJWT.getJwt ( request ) );
    }

    @RequestMapping(value = "/add-holiday", method = RequestMethod.POST)
    public String addHoliday(@ModelAttribute HolidayDay holiday, HttpServletRequest request) {
        logger.info ( "Adding new holiday .." );
        holidaysService.addHoliday ( currentJWT.getJwt ( request ),holiday );
        return "redirect:/list_Parameters";
    }


    @RequestMapping(value = "/supp-holiday", method = RequestMethod.GET)
    public String deleteHoliday(@ModelAttribute HolidayDay holiday, HttpServletRequest request) {
        logger.info ( "Deleting holiday .." );
        if (!getHolidaysList ( request ).isEmpty () && holiday.getId () != null) {
            holidaysService.deleteHoliday ( currentJWT.getJwt ( request ), holiday.getId ());
        }
        return "redirect:/list_Parameters";
    }
}
