package org.Ebanking.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@Data @ToString @AllArgsConstructor @NoArgsConstructor
public class Account {
    private Long id;
    private Long accountNum;
    private Date accountOpeningDate;
    private Double accountBalance;
    private Double accountLimit;
    private Long clientId;
}
