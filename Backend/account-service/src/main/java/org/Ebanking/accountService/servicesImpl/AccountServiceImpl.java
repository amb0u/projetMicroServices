package org.Ebanking.accountService.servicesImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Ebanking.accountService.Repositories.CurrentAccountRepository;
import org.Ebanking.accountService.classes.MyProcessor;
import org.Ebanking.accountService.entities.CurrentAccount;
import org.Ebanking.accountService.classes.Recharge;
import org.Ebanking.accountService.classes.Virement;
import org.Ebanking.accountService.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;


@EnableBinding(MyProcessor.class)
public class AccountServiceImpl implements AccountService {

    @Autowired
    CurrentAccountRepository accountRepository;

    @Autowired
    MyProcessor processor;


    @Override
    public Boolean creditAccount(Long accountNum, Double amount) {
        if (accountRepository.existsByAccountNum ( accountNum )){
            CurrentAccount account = accountRepository.findCurrentAccountByAccountNum(accountNum);
            account.setAccountBalance(account.getAccountBalance() + amount);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public Boolean debitAccount(Long accountNum, Double amount) {
        if (accountRepository.existsByAccountNum ( accountNum )) {
            CurrentAccount account = accountRepository.findCurrentAccountByAccountNum ( accountNum );
            if (amount <= account.getAccountBalance ()) {
                    account.setAccountBalance ( account.getAccountBalance () - amount );
                    accountRepository.save ( account );
                    return true;
            }
        }
        return false;
    }

    @Override
    @SendTo(MyProcessor.OUTPUT2)
    @StreamListener(target = MyProcessor.INPUT2)
    public Virement processVirement(String payload) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        Virement transaction = obj.readValue(payload, Virement.class);
        Long accountNum = transaction.getAccountNum();
        Long accountDes = transaction.getDestinationAccountNum();
        Double amount = transaction.getAmount();

        if ( debitAccount(accountNum,amount) ){
                creditAccount(accountDes,amount);
                transaction.setRemainingBalance (accountRepository.findCurrentAccountByAccountNum ( accountNum ).getAccountBalance ());

            return transaction;
        }
        return null;
    }



    @Override
    @SendTo(MyProcessor.OUTPUT1)
    @StreamListener(target = MyProcessor.INPUT1)
    public Recharge processRecharge(String payload) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        Recharge transaction = obj.readValue(payload, Recharge.class);
        Long accountNum = transaction.getAccountNum();
        Double amount = transaction.getAmount();

        if ( debitAccount(accountNum,amount) ){
            transaction.setRemainingBalance (accountRepository.findCurrentAccountByAccountNum ( accountNum ).getAccountBalance ());
            return transaction;
        }

        return null;
    }


}
