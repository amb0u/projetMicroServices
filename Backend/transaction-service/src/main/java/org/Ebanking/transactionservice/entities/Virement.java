package org.Ebanking.transactionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Virement extends Transaction{

    @NotNull
    private Long destinationAccountNum;

    @NotNull
    private String motif;

    public Virement(Long id, Date transactionDate, Double amount, Long accountNum, Double remainingBalance, Long destinationAccountNum, String motif) {
        super(id, transactionDate, amount, accountNum, remainingBalance);
        this.destinationAccountNum=destinationAccountNum;
        this.motif=motif;
    }


}
