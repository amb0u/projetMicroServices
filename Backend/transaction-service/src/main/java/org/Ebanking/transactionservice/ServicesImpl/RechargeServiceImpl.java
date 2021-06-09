package org.Ebanking.transactionservice.ServicesImpl;

import org.Ebanking.transactionservice.classes.MyProcessor;
import org.Ebanking.transactionservice.entities.Recharge;
import org.Ebanking.transactionservice.repositories.RechargeRepository;
import org.Ebanking.transactionservice.services.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;


@EnableBinding(MyProcessor.class)
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    RechargeRepository rechargeRepository;

    @Override
    @StreamListener(target = MyProcessor.INPUT1)
    public void listen(Recharge transaction) {
        transaction.setId ( null );
        rechargeRepository.save(transaction);
        System.out.println("Recharge registered Successfully");
    }
}
