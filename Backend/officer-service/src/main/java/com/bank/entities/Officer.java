package com.bank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String address;
    private Date birthDay;
    @Column(unique = true)
    private String cin;
    private String phoneNumber;
    private String gender;
    private String role;
    private Long agencyId;

}
