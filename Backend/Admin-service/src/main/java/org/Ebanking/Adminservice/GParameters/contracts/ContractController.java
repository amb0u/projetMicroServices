package org.Ebanking.Adminservice.GParameters.contracts;

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
public class ContractController {
    @Autowired
    ParametersService contractService;
    @Autowired
    CurrentJWT currentJWT;
    Logger logger =  LoggerFactory.getLogger( ContractController.class);

    public List<ContratType> getContractsList(HttpServletRequest request) {
        logger.info ( "Getting list-Contracts .." );
        return contractService.getContratTypes ( currentJWT.getJwt ( request ) );
    }

    @RequestMapping(value = "/add-contract", method = RequestMethod.POST)
    public String addContract(@ModelAttribute ContratType contratType, HttpServletRequest request) {
        logger.info ( "Adding new Contract .." );
        contractService.addContratType ( currentJWT.getJwt ( request ),contratType );
        return "redirect:/list_Parameters";
    }


    @RequestMapping(value = "/supp-contract", method = RequestMethod.GET)
    public String deleteContract(@ModelAttribute ContratType contrat, HttpServletRequest request) {
        logger.info ( "Deleting Contract .." );
        if (!getContractsList ( request ).isEmpty () && contrat.getId ()!=null) {
            contractService.deleteContratType ( currentJWT.getJwt ( request ), contrat.getId ());
        }
        return "redirect:/list_Parameters";
    }

}
