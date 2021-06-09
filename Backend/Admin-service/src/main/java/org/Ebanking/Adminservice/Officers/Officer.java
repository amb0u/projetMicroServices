package org.Ebanking.Adminservice.Officers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Officer {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String birthDay;
        private String cin;
        private String phoneNumber;
        private String gender;
        private String role;
        private Long agencyId;
}

