package org.Ebanking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Column(unique=true)
    private String email;
    @NotNull
    private String address;
    @NotNull
    @Temporal ( TemporalType.DATE )
    private Date birthDay;
    @NotNull
    @Column(unique=true)
    private String cin;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String operator;
    @NotNull
    private String gender;
    @NotNull
    private String status;
    @NotNull
    private String contract;
    @NotNull
    @Temporal ( TemporalType.DATE )
    private Date creationDate;
    @NotNull
    private Long agencyId;
    @Column(unique=true)
    private Long currentAccountNum; // N° de compte courant
    @Column(unique=true)
    private Long savingsAccountNum; // N° de compte Epargne

}
