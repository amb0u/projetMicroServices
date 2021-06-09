package org.Ebanking.Adminservice.GParameters;

import org.Ebanking.Adminservice.GParameters.contracts.ContractController;
import org.Ebanking.Adminservice.GParameters.contracts.ContratType;
import org.Ebanking.Adminservice.GParameters.currency.Currency;
import org.Ebanking.Adminservice.GParameters.currency.CurrencyController;
import org.Ebanking.Adminservice.GParameters.holidays.HolidayDay;
import org.Ebanking.Adminservice.GParameters.holidays.HolidaysController;
import org.Ebanking.Adminservice.GParameters.roles.Role;
import org.Ebanking.Adminservice.GParameters.roles.RoleController;
import org.Ebanking.Adminservice.GParameters.status.Status;
import org.Ebanking.Adminservice.GParameters.status.StatusController;
import org.Ebanking.Adminservice.Officers.OfficerController;
import org.Ebanking.Adminservice.global.CurrentJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class GeneralController {
    @Autowired
    CurrencyController currencyController;
    @Autowired
    ContractController contractController;
    @Autowired
    RoleController roleController;
    @Autowired
    HolidaysController holidaysController;
    @Autowired
    StatusController statusController;
    @Autowired
    CurrentJWT currentJWT;
    Logger logger =  LoggerFactory.getLogger( OfficerController.class);

    @RequestMapping(value = "/list_Parameters", method = RequestMethod.GET)
    public String getParametersList(HttpServletRequest request, ModelMap model) {
        logger.info ( "Getting list-Parameters.html .." );

        model.addAttribute ( "currency", currencyController.getCurrency ( request  ) );
        model.addAttribute ( "contracts", contractController.getContractsList ( request )  );
        model.addAttribute ( "roles", roleController.getRolesList ( request ));
        model.addAttribute ( "holidays", holidaysController.getHolidaysList ( request ));
        model.addAttribute ( "status", statusController.getStatusList ( request ));

        model.addAttribute ( "emptyCurrency", new Currency (  ) );
        model.addAttribute ( "emptyContract", new ContratType (  ) );
        model.addAttribute ( "emptyRole", new Role (  ) );
        model.addAttribute ( "emptyHoliday",new HolidayDay (  ) );
        model.addAttribute ( "emptyStatus", new Status (  ) );

        Long  contractId=null, roleId=null, holidayId=null, statusId=null;
        model.addAttribute ( "contractId", contractId );
        model.addAttribute ( "roleId",  roleId);
        model.addAttribute ( "holidayId",holidayId );
        model.addAttribute ( "statusId", statusId );

        return "list-Parameters";
    }
}
