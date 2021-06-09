package org.Ebanking.accountService.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Card {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    private String Issuer;
    @NotNull
    @Column(unique=true)
    private Long cardNumber;
    @NotNull
    private Long secretCode;
    @NotNull
    private boolean opposed;
    @NotNull
    @Temporal ( TemporalType.DATE )
    private Date expirationDate;
    @NotNull
    private Long clientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private CurrentAccount currentAccount;

}
