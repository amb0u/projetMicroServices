package org.Ebanking.classes;

import lombok.Data;

import java.util.Date;

@Data
public class addCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Date birthDay;
    private String cin;
    private String phoneNumber;
    private String operator;
    private String gender;
    private String status;
    private String contract;
    private Date creationDate;
    private Long agencyId;
}
