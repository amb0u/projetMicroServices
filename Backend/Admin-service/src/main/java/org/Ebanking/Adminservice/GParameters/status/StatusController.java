package org.Ebanking.Adminservice.GParameters.status;

import org.Ebanking.Adminservice.GParameters.ParametersService;
import org.Ebanking.Adminservice.Officers.OfficerController;
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
public class StatusController {
    @Autowired
    ParametersService statusService;
    @Autowired
    CurrentJWT currentJWT;
    Logger logger =  LoggerFactory.getLogger( OfficerController.class);

    public List<Status> getStatusList(HttpServletRequest request) {
        logger.info ( "Getting list-status .." );
        return statusService.getStatus ( currentJWT.getJwt (request) );
    }

    @RequestMapping(value = "/add-status", method = RequestMethod.POST)
    public String addStatus(@ModelAttribute Status status, HttpServletRequest request) {
        logger.info ( "Adding new Status .." );
        statusService.addStatus ( currentJWT.getJwt ( request ),status );
        return "redirect:/list_Parameters";
    }

    @RequestMapping(value = "/supp-status", method = RequestMethod.GET)
    public String deleteStatus(@ModelAttribute Status status, HttpServletRequest request) {
        logger.info ( "Deleting Status .." );
        if (!getStatusList ( request ).isEmpty () && status.getId ()!=null) {
            statusService.deleteStatus ( currentJWT.getJwt ( request ), status.getId ());
        }
        return "redirect:/list_Parameters";
    }

}
