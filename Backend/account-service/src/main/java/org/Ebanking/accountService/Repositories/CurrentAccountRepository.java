package org.Ebanking.accountService.Repositories;

import org.Ebanking.accountService.entities.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CurrentAccountRepository extends JpaRepository <CurrentAccount,Long> {

    public CurrentAccount findCurrentAccountByClientId(Long clientId);
    public CurrentAccount findCurrentAccountByAccountNum(Long currentAccountNum);
    public Boolean existsByAccountNum( Long AccountNum);
    public Boolean existsByClientId( Long cliendId);



    @Transactional
    @Modifying
    @Query("delete from CurrentAccount  a where a.clientId=:clientId")
    public void deleteCurrentAccountByClientId(@Param ( "clientId" ) Long clientId);

}
