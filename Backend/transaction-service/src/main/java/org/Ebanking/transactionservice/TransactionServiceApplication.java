package org.Ebanking.transactionservice;

import org.Ebanking.transactionservice.classes.MyProcessor;
import org.Ebanking.transactionservice.entities.Recharge;
import org.Ebanking.transactionservice.entities.Virement;
import org.Ebanking.transactionservice.repositories.RechargeRepository;
import org.Ebanking.transactionservice.repositories.VirementRepository;
import org.Ebanking.transactionservice.serviceJwt.CurrentJWT;
import org.Ebanking.transactionservice.serviceJwt.CurrentJWTImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Calendar;
import java.util.Date;


@SpringBootApplication
@EnableFeignClients
public class TransactionServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}


	@Bean
	CurrentJWT getJwt() {
		return new CurrentJWTImpl ();
	}


	@Autowired
	VirementRepository virementRepository;

	@Autowired
	RechargeRepository rechargeRepository;

	@Autowired
	MyProcessor processor;


	@Override
	public void run(String... args) throws Exception {

					/***** Virements !**/
		Calendar lastDate = Calendar.getInstance();


		lastDate.add(Calendar.DATE,-59);
		Virement v1 = new Virement ( 3L, lastDate.getTime (), 200d,1111L,200000d, 222L," cadeau" );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v1 ).build () );

		virementRepository.save(v1);

		lastDate.add(Calendar.DATE,10);
		Virement v2 = new Virement ( null, lastDate.getTime (), 120d,1111L,200000d, 3333L," cadeau " );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v2 ).build () );
		virementRepository.save(v2);

		lastDate.add ( Calendar.DATE, 20);
		Virement v3 = new Virement ( 3L, lastDate.getTime (), 200d,1111L,200000d, 222L," don" );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v3 ).build () );
		virementRepository.save(v3);


		lastDate.add ( Calendar.DATE, 10);
		Virement v4 = new Virement ( null, lastDate.getTime (), 100d,1111L,200000d, 3333L,"don " );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v4 ).build () );
		virementRepository.save(v4);

		Virement v5 = new Virement ( null, new Date (  ), 300d,1111L,200000d, 222L," remboursemnet " );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v5 ).build () );
		virementRepository.save(v5);

		lastDate.add(Calendar.DATE,-900);
		Virement v6 = new Virement ( 3L, lastDate.getTime (), 2000d,1111L,200000d, 3333L," don" );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v1 ).build () );

		virementRepository.save(v6);

		lastDate.add(Calendar.DATE,1080);
		Virement v7 = new Virement ( null, lastDate.getTime (), 1250d,3333L,200000d, 5555L," remboursement " );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v2 ).build () );
		virementRepository.save(v7);

		lastDate.add ( Calendar.DATE, 200);
		Virement v8 = new Virement ( 3L, lastDate.getTime (), 4200d,5555L,500000d, 1111L," don " );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v3 ).build () );
		virementRepository.save(v8);


		lastDate.add ( Calendar.DATE, 1000);
		Virement v9 = new Virement ( null, lastDate.getTime (), 150d,3333L,200000d, 6666L,"cadeau " );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v4 ).build () );
		virementRepository.save(v9);

		Virement v10 = new Virement ( null, new Date (  ), 300d,1111L,200000d, 222L," don " );
		processor.myOutput2 ().send ( MessageBuilder.withPayload ( v5 ).build () );
		virementRepository.save(v10);

							/***** Recharges !**/
		Recharge r1 = new Recharge ( null, lastDate.getTime (), 100d,1111L,200000d, "IAM"," 0602468922 " );
		processor.myOutput1().send(MessageBuilder.withPayload(r1).build());
		rechargeRepository.save(r1);

		lastDate.add ( Calendar.DATE, 2);
		Recharge r2 = new Recharge ( null,lastDate.getTime (), 10d,1111L,200000d, "IAM"," 0602468922 " );
		processor.myOutput1().send(MessageBuilder.withPayload(r2).build());
		rechargeRepository.save(r2);

		Recharge r3 = new Recharge ( null, new Date (  ), 200d,1111L,200000d, "IAM"," 0602468922 " );
		processor.myOutput1().send(MessageBuilder.withPayload(r3).build());
		rechargeRepository.save(r3);

		Recharge r4 = new Recharge ( null, new Date (  ), 100d,3333L,200000d, "IAM"," 0602468922 " );
		processor.myOutput1().send(MessageBuilder.withPayload(r3).build());
		rechargeRepository.save(r4);

		Recharge r5 = new Recharge ( null, new Date (  ), 20d,3333L,200000d, "IAM"," 0602468922 " );
		processor.myOutput1().send(MessageBuilder.withPayload(r3).build());
		rechargeRepository.save(r5);
	}
}
