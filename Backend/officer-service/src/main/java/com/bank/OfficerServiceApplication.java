package com.bank;

import com.bank.entities.Officer;
import com.bank.repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class OfficerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(OfficerRepository officerRepository){
        return args -> {
        officerRepository.save(new Officer(1L,"Mounib","Elboujbaoui","mounib@bfrontend.com","Mcdo , Marrakech",new Date(),"P12364","023659485","male","directeur",1L));
        officerRepository.save(new Officer(2L,"Amine","Sedgui","amine@frontend.com","Media, Marrakech",new Date(),"JC123964","0903659485","male","Respo des Clients",1L));
        officerRepository.save(new Officer(3L,"Ghani","Kaddouri","ghani@frontend.com","Hay nahda , Marrakech",new Date(),"L100894","0100059485","male","Respo des de Marketing",1L));
        officerRepository.save(new Officer(4L,"Salma","hamdi","salma@bfrontend.com","Massira, Ouarzazate",new Date(),"P14894","06559487","female","Respo des Clients",4L));
        officerRepository.save(new Officer(5L,"ali","Sedraty","ali@bfrontend.com","Mcdo , Mohmadia",new Date(),"M78464","066159635","male","Respo des Clients",2L));
        };
    }

}
