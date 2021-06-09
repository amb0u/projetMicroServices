package org.Ebanking.accountService.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class SavingsAccount extends Account {
    @NotNull
    private float rate;

    public SavingsAccount(Long id, Long accountNum, Date accountOpeningDate, double accountBalance, double accountLimit, Long clientId, float rate) {
        super(id,accountNum, accountOpeningDate, accountBalance, accountLimit, clientId);
        this.rate = rate;
    }

    public void AddRateToBalance(){
            //TODO change the Balance according to the Rate.Every year !
    }
}
