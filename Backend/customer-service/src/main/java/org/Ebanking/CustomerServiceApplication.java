package org.Ebanking;


import org.Ebanking.classes.AccountService;
import org.Ebanking.classes.currentAccount;
import org.Ebanking.classes.savingsAccount;
import org.Ebanking.entities.Customer;
import org.Ebanking.repositories.CustomerRepository;
import org.Ebanking.service.CurrentJWT;
import org.Ebanking.service.CurrentJWTImpl;
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
public class CustomerServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}


	@Bean
	CurrentJWT getJwt() {
		return new CurrentJWTImpl();
	}

	@Autowired
	AccountService accountService;

	@Autowired
	CustomerRepository clientRepository;

	@Override
	public void run(String... args) throws Exception {


		//TODO remove for mode prod !
		//currentAccount c1CurrentAccount = accountService.findCurrentAccountByCustomerId(1L);
		//savingsAccount c1savingsAccount = accountService.findSavingsAccountByCustomerId(1L);
		clientRepository.save(new Customer(1L, "Abdou", "Ahbane", "ahbane@backend.com", "Mhamid, Marrakech", new SimpleDateFormat("dd/MM/yyyy").parse("17/01/1999") , "JP87256", "09734567894567", "Orange", "Masculin", "Actif", "Personnel", new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2020"), 1L, 1111L, 2222L));
		clientRepository.save(new Customer(2L, "Manal", "Outaleb", "manal@backend.com", " Somewhere, Marrakech", new SimpleDateFormat("dd/MM/yyyy").parse("17/01/1999") , "A5332788", "0623658102", "IAM", "Feminin", "Actif", "Personnel",  new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2020"), 1L, 3333L, 4444L));
		clientRepository.save(new Customer(3L, "Abdelilah", "Wahman", "wahman@backend.com", "Centre ville ,Marrakech", new SimpleDateFormat("dd/MM/yyyy").parse("11/05/1997") , "M887269", "06756789446", "Orange", "Masculin", "Actif", "Personnel", new SimpleDateFormat("dd/MM/yyyy").parse("25/04/2020"), 1L, 5555L, null));
		clientRepository.save(new Customer(4L, "Lamia", "Mahraoui", "lamia@gmail.com", "Gare routiere, Marrakech", new SimpleDateFormat("dd/MM/yyyy").parse("31/02/1996") , "L586269", "06269495631", "IAM", "Feminin", "Actif", "Personnel", new Date(), 1L, null, null));
		//TODO remove for mode prod !
		//currentAccount c2CurrentAccount = accountService.findCurrentAccountByCustomerId(2L);
	//	savingsAccount c2savingsAccount = accountService.findSavingsAccountByCustomerId(2L);

		clientRepository.save(new Customer(5L, "Mehdi", "Outaleb", "mehdi@gmail.com", "Massira, Ouarzazate", new SimpleDateFormat("dd/MM/yyyy").parse("11/08/1959") , "P587269", "0626985630", "INWI", "Masculin", "Actif", "Personnel", new SimpleDateFormat("dd/MM/yyyy").parse("27/05/2020"), 4L, null, 6666L));
		clientRepository.findAll().forEach(System.out::println);
	}



}

