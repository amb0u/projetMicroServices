package com.Ebanking;

import com.Ebanking.entities.Dstatus;
import com.Ebanking.Repositories.DemandeRepository;
import com.Ebanking.entities.Demande;
import com.Ebanking.service.CurrentJWT;
import com.Ebanking.service.CurrentJWTImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class RequestServiceApplication implements CommandLineRunner {
    @Bean
    CurrentJWT getJwt(){
        return new CurrentJWTImpl();
    }
    @Autowired
    private DemandeRepository demandeRepository;
    public static void main(String[] args) {
        SpringApplication.run(RequestServiceApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        demandeRepository.save(new Demande(new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2020"), Dstatus.traite, "Je veux récupérer ma carte VISA",1L,"Demande de carte",1L));
        demandeRepository.save(new Demande(new Date(), Dstatus.enCours, "Mon carnet chèque est vide", 1L,"Demande de carnet de chèque",1L));
        demandeRepository.save(new Demande(new SimpleDateFormat("dd/MM/yyyy").parse("29/06/2020"),Dstatus.enCours, "Je veux activer l'achat en ligne avec ma carte", 1L,"Activation d'achat en ligne",1L));
        demandeRepository.save(new Demande(new SimpleDateFormat("dd/MM/yyyy").parse("29/05/2020"), Dstatus.traite, "Mes transactions bancaires ne passent plus", 2L,"Problème",1L));
        demandeRepository.save(new Demande(new Date(), Dstatus.enCours, "Je n'ai plus besoin de mon compte d'epargne", 5L,"Cloturer mon compte d'epargne",4L));
        demandeRepository.save(new Demande(new Date(), Dstatus.enCours, "Je veux ouvrir un compte d'epargne", 3L,"Ouverture compte d'epargne",1L));
        demandeRepository.save(new Demande(new SimpleDateFormat("dd/MM/yyyy").parse("26/06/2020"), Dstatus.traite, "Je veux ouvrir un compte courant", 2L,"Ouverture compte courant",1L));
        demandeRepository.save(new Demande(new SimpleDateFormat("dd/MM/yyyy").parse("29/06/2020"), Dstatus.enCours, "Quels sont vos horaires d'ouverture", 4L,"Renseignements",1L));
    }
}
