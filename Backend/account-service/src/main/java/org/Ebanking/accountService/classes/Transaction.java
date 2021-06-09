package org.Ebanking.accountService.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Transaction {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Date transactionDate = new Date();
    @NotNull
    private Double amount;
    @NotNull
    private Long accountNum;
    @NotNull
    private Double remainingBalance;


}
