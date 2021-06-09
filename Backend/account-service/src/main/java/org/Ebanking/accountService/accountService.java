package org.Ebanking.accountService;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.Ebanking.accountService.Repositories.CardRepository;
import org.Ebanking.accountService.Repositories.CurrentAccountRepository;
import org.Ebanking.accountService.Repositories.SavingsAccountRepository;
import org.Ebanking.accountService.entities.Card;
import org.Ebanking.accountService.entities.CurrentAccount;
import org.Ebanking.accountService.entities.SavingsAccount;
import org.Ebanking.accountService.serviceJwt.CurrentJWT;
import org.Ebanking.accountService.serviceJwt.CurrentJWTImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class accountService implements CommandLineRunner {

    public static void main(String[] args)
    {
        SpringApplication.run(accountService.class, args);
    }

    @Bean
    CurrentJWT getJwt() {
        return new CurrentJWTImpl();
    }
    @Autowired
    CurrentAccountRepository currentAccountRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    @Override
    public void run(String... args) throws Exception {


        CurrentAccount c1 = new CurrentAccount ( 1L, 1111L,new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2020"), 200000d,2000000d,1l) ;
        CurrentAccount c2 = new CurrentAccount ( 2L, 3333L,new SimpleDateFormat("dd/MM/yyyy").parse("21/04/2020"), 30000d,2000000d,2l) ;
        CurrentAccount c3 = new CurrentAccount ( 3L, 5555L,new Date(), 30200d,5000000d,3l) ;
        currentAccountRepository.save ( c1 );
        currentAccountRepository.save ( c2 );
        currentAccountRepository.save ( c3 );

        Card card1 = new Card(null, "MasterCard",1010L, 2222L,false, new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2020"),1L,c1 );
        Card card2 = new Card(null, "VISA",2020L, 2822L,false, new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2020"),1L,c1 );
        cardRepository.save ( card1 );
        cardRepository.save ( card2 );

        savingsAccountRepository.save ( new SavingsAccount ( 1L, 2222L,new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2020"), 188888d,2000000d,1l, 0.2f) );
        savingsAccountRepository.save ( new SavingsAccount ( 2L, 4444L,new SimpleDateFormat("dd/MM/yyyy").parse("21/04/2020"), 188888d,2000000d,2l, 0.3f) );
        savingsAccountRepository.save ( new SavingsAccount ( 3L, 6666L,new SimpleDateFormat("dd/MM/yyyy").parse("29/06/2020"), 20000d,2000000d,5l, 0.7f) );
    }
}
