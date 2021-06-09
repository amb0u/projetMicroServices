package com.bank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;



@AllArgsConstructor @NoArgsConstructor @Data
public class UpdateUser {

    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String newPassword;
    @NonNull
    private String confirmPassword;
}
