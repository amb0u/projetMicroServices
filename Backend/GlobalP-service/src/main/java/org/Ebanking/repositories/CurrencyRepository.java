package org.Ebanking.repositories;

import org.Ebanking.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;


@CrossOrigin("*")
@RepositoryRestResource(path = "api")
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Currency r  set r.symbol=:symbol where r.id=:id")
    public void editCurrency(@Param( "symbol" ) String symbol, @Param ( "id" )  Long id);

}
