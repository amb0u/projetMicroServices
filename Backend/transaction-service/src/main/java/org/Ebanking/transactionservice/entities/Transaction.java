package org.Ebanking.transactionservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
@Data @NoArgsConstructor @ToString
public class Transaction {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal ( TemporalType.DATE )
    private Date transactionDate = new Date() ;
    @NotNull
    private Double amount;
    @NotNull
    private Long accountNum;
    private Double remainingBalance;

    public  Transaction(Long id, Date transactionDate, Double amount, Long accountNum, Double remainingBalance ){
        this.id = id;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.accountNum = accountNum;
        this.remainingBalance = remainingBalance;
    }

}
