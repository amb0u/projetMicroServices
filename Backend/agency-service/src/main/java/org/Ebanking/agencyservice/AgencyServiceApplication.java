package org.Ebanking.agencyservice;

import org.Ebanking.agencyservice.entities.Agency;
import org.Ebanking.agencyservice.repositories.AgencyRepository;
import org.Ebanking.agencyservice.service.CurrentJWT;
import org.Ebanking.agencyservice.service.CurrentJWTImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class AgencyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgencyServiceApplication.class, args);
	}
	@Bean
	CurrentJWT getJwt(){
		return new CurrentJWTImpl();
	}

	@Bean
	CommandLineRunner start(AgencyRepository agencyRepository){
		return args -> {
			Calendar dd = Calendar.getInstance ();
			dd.add ( Calendar.DATE,-300 );

			agencyRepository.save ( new Agency ( null, "Marrakech Agency", "Massira, Marrakech ","0601622750","Massira@Ebanking.com", "+212062889023",dd.getTime (), 1L ) );
			dd.add ( Calendar.DATE,20 );
			agencyRepository.save ( new Agency ( null, "Mohammadia Agency", "LeQassba, Mohammadia","06033887876","Mohamadia@Ebanking.com", "+2120600800983",dd.getTime (), 2L ) );
			dd.add ( Calendar.DATE,120 );
			agencyRepository.save ( new Agency ( null, "Sidi ifni Agency", "Centre ville, Sidi ifni","0600662956","SidiIfni@Ebanking.com", "+212062019123",dd.getTime (), 1L ) );
			dd.add ( Calendar.DATE,80 );
			agencyRepository.save ( new Agency ( null, "Ouarzazate Agency", "Centre ville, Ouarzazate","0609939776","Ourzazate@Ebanking.com", "+21209888877683",dd.getTime (), 2L ) );

			agencyRepository.save ( new Agency ( null, "Beni Mellal Agency", "Qassba, Beni Mellal","0608632756","BeniMellal@Ebanking.com", "+212062811123",new Date (  ), 1L ) );
			agencyRepository.save ( new Agency ( null, "Aoulouz Agency", "Aoulouz, Taroudant","06066329876", "Aoulouz@Ebanking.com","+21206288877683",new Date (  ), 2L ) );
		};
	}
}
