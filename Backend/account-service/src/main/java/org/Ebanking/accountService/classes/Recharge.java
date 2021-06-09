package org.Ebanking.accountService.classes;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Recharge extends Transaction {

    @NotNull
    private String operator;
    @NotNull
    private String numRecharge;


    public Recharge(Long id, Date transactionDate, @NotNull Double amount, @NotNull Long accountNum,Double remainingBalance,@NotNull String operator, @NotNull String numRecharge ) {
        super(id, transactionDate, amount, accountNum, remainingBalance);
        this.operator=operator;
        this.numRecharge=numRecharge;
    }
}
