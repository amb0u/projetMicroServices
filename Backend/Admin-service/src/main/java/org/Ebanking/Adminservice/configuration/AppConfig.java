package org.Ebanking.Adminservice.configuration;

import org.Ebanking.Adminservice.global.CurrentJWT;
import org.Ebanking.Adminservice.global.CurrentJWTImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

     @Bean
    CurrentJWT getCurrentJWT(){
         return  new CurrentJWTImpl ();
     }

}
