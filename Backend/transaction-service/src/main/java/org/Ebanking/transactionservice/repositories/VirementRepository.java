package org.Ebanking.transactionservice.repositories;

import org.Ebanking.transactionservice.entities.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface VirementRepository extends JpaRepository<Virement,Long> {

        Collection<Virement> findVirementByAccountNum(Long accountNum);

        Collection<Virement> findVirementByTransactionDateBetween(Date start, Date end);

        List<Virement> findVirementByTransactionDateBetweenOrderByTransactionDate(Date start, Date end);
}
