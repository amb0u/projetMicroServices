package com.bank.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName; //email !!
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean activated;
    @OneToOne(fetch = FetchType.EAGER)
    private Role role;
}
