package org.Ebanking.accountService.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class Virement extends Transaction {

    @NotNull
    private Long destinationAccountNum;

    @NotNull
    private String motif;

    public Virement(Long id, Date transactionDate, @NotNull Double amount, @NotNull Long accountNum, Double remainingBalance, @NotNull Long destinationAccountNum, @NotNull String motif) {
        super(id, transactionDate, amount, accountNum, remainingBalance);
        this.destinationAccountNum=destinationAccountNum;
        this.motif=motif;
    }
}
