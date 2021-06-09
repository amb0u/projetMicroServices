package org.Ebanking.Adminservice.GParameters.currency;

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
import java.util.Optional;


@Controller
public class CurrencyController {
    @Autowired
    ParametersService currencyService;
    @Autowired
    CurrentJWT currentJWT;
    Logger logger =  LoggerFactory.getLogger( CurrencyController.class);


    public Optional<Currency> getCurrency(HttpServletRequest request) {
        logger.info ( "Getting Currency .." );

        return currencyService.getCurrency (currentJWT.getJwt ( request ));
    }

    @RequestMapping(value = "/add-devise", method = RequestMethod.POST)
    public String addCurrency(@ModelAttribute Currency currency, HttpServletRequest request) {
        logger.info ( "Adding the Currency .." );
        currencyService.addCurrency ( currentJWT.getJwt ( request ),currency );
        return "redirect:/list_Parameters";
    }

    @RequestMapping(value = "/mod-devise", method = RequestMethod.POST)
    public String editCurrency(@ModelAttribute Currency currency, HttpServletRequest request) {
        logger.info ( "Editing the Currency .." );
        currencyService.editCurrency ( currentJWT.getJwt ( request ),currency );
        return "redirect:/list_Parameters";
    }

    @RequestMapping(value = "/supp-devise", method = RequestMethod.GET)
    public String deleteCurrency( HttpServletRequest request) {
        logger.info ( "Deleting the Currency .." );
        if (getCurrency ( request ).isPresent ()) {
                currencyService.deleteCurrency ( currentJWT.getJwt ( request ) );
        }
        return "redirect:/list_Parameters";
    }


}
