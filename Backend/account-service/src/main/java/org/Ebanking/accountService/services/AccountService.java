package org.Ebanking.accountService.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.Ebanking.accountService.classes.Recharge;
import org.Ebanking.accountService.classes.Virement;

public interface AccountService {

    public Boolean creditAccount(Long cardNum, Double amount);
    public Boolean debitAccount(Long cardNum, Double amount);
    public Virement processVirement(String transaction) throws JsonProcessingException;
    public Recharge processRecharge(String transaction) throws JsonProcessingException;


}
