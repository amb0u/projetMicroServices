package org.Ebanking.accountService.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@MappedSuperclass
@Data @NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique=true)
    private Long accountNum;
    @NotNull
    @Temporal ( TemporalType.DATE )
    private Date accountOpeningDate;
    @NotNull
    private double accountBalance;
    @NotNull
    private double accountLimit;
    @NotNull
    @Column(unique=true)
    private Long clientId;
}
