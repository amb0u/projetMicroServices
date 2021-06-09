package org.Ebanking.Adminservice.Customer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Customer {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String birthDay;
        private String cin;
        private String phoneNumber;
        private String operator;
        private String gender;
        private String status;
        private String contract;
        private String creationDate;
        private Long agencyId;
        private Long currentAccountNum;
        private Long savingsAccountNum;
}

