package com.bank;

import com.bank.entities.Role;
import com.bank.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class SecServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }
   @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {
          accountService.save(new Role(null, "CLIENT"));
          accountService.save(new Role(null, "OFFICER"));
          accountService.save(new Role(null, "ADMIN"));
          accountService.saveUser ( "atlas@gmail.com", "12345", "ADMIN" );
          accountService.saveUser ( "manal@backend.com", "12345", "CLIENT" );
          accountService.saveUser ( "amine@frontend.com", "12345", "OFFICER" );

          accountService.saveUser ( "ameur@gmail.com", "12345", "ADMIN" );
          accountService.saveUser ( "wahman@backend.com", "12345", "CLIENT" );
          accountService.saveUser ( "mounib@frontend.com", "12345", "OFFICER" );

          accountService.saveUser ( "ahbane@backend.com", "12345", "CLIENT" );
          accountService.saveUser ( "ghani@frontend.com", "12345", "OFFICER" );

          accountService.saveUser ( "lamia@gmail.com", "12345", "CLIENT" );
          accountService.saveUser ( "salma@gmail.com", "salma", "OFFICER" );

          accountService.saveUser ( "mehdi@gmail.com", "12345", "CLIENT" );
          accountService.saveUser ( "ali@gmail.com", "12345", "OFFICER" );
        };
   }
   @Bean
   BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
   }
}
