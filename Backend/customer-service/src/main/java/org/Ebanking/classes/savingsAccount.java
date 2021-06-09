package org.Ebanking.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class savingsAccount extends Account{

    private  float rate;

    public savingsAccount(Long id, Long accountNum, Date accountOpeningDate, Double accountBalance, Double accountLimit, Long clientId,float rate) {
        super ( id, accountNum, accountOpeningDate, accountBalance, accountLimit, clientId );
        this.rate = rate;
    }

}
