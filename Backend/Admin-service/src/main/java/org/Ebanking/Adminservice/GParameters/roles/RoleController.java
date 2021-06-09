package org.Ebanking.Adminservice.GParameters.roles;

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
public class RoleController {
    @Autowired
    ParametersService roleService;
    @Autowired
    CurrentJWT currentJWT;
    Logger logger =  LoggerFactory.getLogger( RoleController.class);

    public List<Role> getRolesList(HttpServletRequest request) {
        logger.info ( "Getting list-roles .." );
        return roleService.getRoles ( currentJWT.getJwt ( request ) );
    }

    @RequestMapping(value = "/add-role", method = RequestMethod.POST)
    public String addRole(@ModelAttribute Role role, HttpServletRequest request) {
        logger.info ( "Adding new Role .." );
        roleService.addRole ( currentJWT.getJwt ( request ),role );
        return "redirect:/list_Parameters";
    }


    @RequestMapping(value = "/supp-role", method = RequestMethod.GET)
    public String deleteRole(@ModelAttribute Role role, HttpServletRequest request) {
        logger.info ( "Deleting role .." );
        if (!getRolesList ( request ).isEmpty () && role.getId ()!=null) {
            roleService.deleteRole ( currentJWT.getJwt ( request ), role.getId ());
        }
        return "redirect:/list_Parameters";
    }
}
