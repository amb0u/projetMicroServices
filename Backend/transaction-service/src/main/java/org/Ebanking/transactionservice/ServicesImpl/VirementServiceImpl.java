package org.Ebanking.transactionservice.ServicesImpl;

import org.Ebanking.transactionservice.classes.AccountService;
import org.Ebanking.transactionservice.classes.MyProcessor;
import org.Ebanking.transactionservice.entities.Virement;
import org.Ebanking.transactionservice.repositories.VirementRepository;
import org.Ebanking.transactionservice.services.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.Date;

@EnableBinding(MyProcessor.class)
public class VirementServiceImpl implements VirementService {

    @Autowired
    VirementRepository transactionRepository;

    @Autowired
    AccountService accountService;


    @Override
    @StreamListener(target = MyProcessor.INPUT2)
    public void listen(Virement transaction) {
        transaction.setId ( null );
        transactionRepository.save(transaction);
        System.out.println("Virement registered Successfully");
    }
}
