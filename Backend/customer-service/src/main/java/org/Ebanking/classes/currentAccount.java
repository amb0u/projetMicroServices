package org.Ebanking.classes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data  @ToString @NoArgsConstructor @AllArgsConstructor
public class currentAccount extends Account {
        private Long cardNumber;
        private int verificationCode;

    public currentAccount(Long id, Long accountNum, Date accountOpeningDate, Double accountBalance, Double accountLimit, Long clientId, Long cardNumber, int verificationCode) {
        super ( id, accountNum, accountOpeningDate, accountBalance, accountLimit, clientId );
        this.cardNumber = cardNumber;
        this.verificationCode = verificationCode;
    }
}
