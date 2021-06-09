package org.Ebanking;

import org.Ebanking.entities.*;
import org.Ebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class GlobalPServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalPServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ContratTypeRepository contratTypeRepository, CurrencyRepository currencyRepository, RoleRepository roleRepository, StatusRepository statusRepository, HolidayDayRepository holidayDayRepository){
		return args-> {
				currencyRepository.save ( new Currency ( 1L, "MAD" ) );
				contratTypeRepository.save ( new ContratType ( null, "Personnel", "For individual persons" ) );
				contratTypeRepository.save ( new ContratType ( null, "Entreprise", "For Companies" ) );
				roleRepository.save ( new Role ( null, "Directeur", "Gere Un Agence" ) );
				roleRepository.save ( new Role ( null, "Resp. Clients", "Responsables des services Clients" ) );
				statusRepository.save ( new Status ( null, "Actif", "Pour un compte qu est active" ) );
				statusRepository.save ( new Status ( null, "Passif", "Pour un compte qu est desactivé" ) );
				holidayDayRepository.save ( new HolidayDay ( null, "Aid Aldha ", new Date () ) );
				Calendar c = Calendar.getInstance ();
				c.add ( Calendar.DATE, -120 );
				holidayDayRepository.save ( new HolidayDay ( null, "Aid alfitr ", c.getTime () ) );
		};
	}
}
