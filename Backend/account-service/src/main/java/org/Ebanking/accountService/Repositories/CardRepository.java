package org.Ebanking.accountService.Repositories;

import org.Ebanking.accountService.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;

public interface CardRepository extends JpaRepository <Card,Long> {

    public Collection<Card> findCardByClientId(Long clientId);
    public Boolean existsByClientId( Long clientId);
    public boolean existsById (Long id);
    public void deleteByClientId( Long clientId);
    @Transactional
    @Modifying
    @Query("UPDATE Card c  set c.opposed=:opposed where c.Id=:id")
    public void editOpposed(@Param( "opposed" ) boolean opposed, @Param ( "id" )  Long id);


}
