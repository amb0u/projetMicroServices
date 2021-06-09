package org.Ebanking.transactionservice.repositories;

import org.Ebanking.transactionservice.entities.Transaction;
import org.springframework.data.rest.core.config.Projection;


@Projection( name = "CustomerAccounts", types = { Transaction.class })
public interface TransactionProjection {
        String getType();
        String getAmount();
        String getCustomerId();
        String getAmountId();
}
