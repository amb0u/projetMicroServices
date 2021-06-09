package org.Ebanking.transactionservice.repositories;

import org.Ebanking.transactionservice.entities.Recharge;
import org.Ebanking.transactionservice.entities.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public interface RechargeRepository extends JpaRepository<Recharge,Long> {

    Collection<Recharge> findRechargeByAccountNum(Long accountNum);

    Collection<Recharge> findRechargeByTransactionDateBetween(Date start, Date end);
}
