package org.Ebanking.Adminservice.login;


import feign.FeignException;
import org.Ebanking.Adminservice.global.CurrentJWT;
import org.Ebanking.Adminservice.security.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    CurrentJWT currentJWT;

    Logger logger =  LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(ModelMap model, HttpServletRequest request, HttpServletResponse response){
        logger.info ( "Getting login.html .." );
        model.addAttribute ( "userLgin", new UserLogin () );
        model.addAttribute ( "error", "" );
        response.addCookie ( currentJWT.resetCookie ( "/" ) );
        return "login";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String sendLogin(@ModelAttribute UserLogin newLogin, ModelMap model, HttpServletResponse response, HttpServletRequest request) throws IOException {
           logger.info ( "POST userLogin to server  .." );
            String jwtToken = null;
            try {
                    ResponseEntity res = authenticationService.sendLogin ( newLogin);
                    jwtToken = String.valueOf ( res.getHeaders ().get ( SecurityConstants.HEADER_STRING ) ).replaceAll ( "\\[|\\]", "" );

                    if ( jwtToken != null ) {
                            String jwt = jwtToken.substring ( SecurityConstants.TOKEN_PREFIX.length () );
                            response.addCookie ( currentJWT.setCookie ( "/", jwt ) );
                            return "redirect:/list_agences";
                    }
            }
            catch ( FeignException e ){
                    logger.info ( "JWT is  null" );
                    model.addAttribute ( "userLgin", new UserLogin () );
                    String error = "Username ou mot de passe incorrect !";
                    model.addAttribute ( "error", error );
                    return "login";
            }
            return "redirect:/login";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String Logout(){
          logger.info ( "Admin is getting Logout" );
          return "redirect:/login";
    }

}
