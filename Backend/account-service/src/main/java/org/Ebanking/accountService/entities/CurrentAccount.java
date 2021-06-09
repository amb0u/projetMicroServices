package org.Ebanking.accountService.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@JsonSerialize
public class CurrentAccount extends Account {

        @OneToMany( mappedBy = "currentAccount",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
        private List<Card> cards;


        public CurrentAccount(Long id, Long accountNum, Date accountOpeningDate, double accountBalance, double accountLimit, Long clientId) {
                super( id,accountNum, accountOpeningDate, accountBalance, accountLimit, clientId);
        }

}
