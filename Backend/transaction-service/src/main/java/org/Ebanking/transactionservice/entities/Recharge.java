package org.Ebanking.transactionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Recharge extends Transaction{

    @NotNull
    private String operator;
    @NotNull
    private String numRecharge;


    public Recharge(Long id, Date transactionDate, Double amount, Long accountNum, Double remainingBalance,String operator,String numRecharge ) {
        super(id, transactionDate, amount, accountNum, remainingBalance);
        this.operator=operator;
        this.numRecharge=numRecharge;
    }


}
