package org.Ebanking.accountService.Repositories;

import org.Ebanking.accountService.entities.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SavingsAccountRepository extends JpaRepository <SavingsAccount,Long> {

    public SavingsAccount findSavingsAccountByClientId(Long clientId);
    public SavingsAccount findSavingsAccountByAccountNum(String accountNum);


    @Transactional
    @Modifying
    @Query("delete from SavingsAccount a where a.clientId=:clientId")
    public void deleteSavingAccountByClientId(@Param("clientId") Long clientId);

    @Transactional
    @Modifying
    @Query("UPDATE SavingsAccount a  set a.accountBalance=:accountBalance where a.accountNum=:accountNum")
    public void ChangeBalance(@Param("accountBalance") double accountBalance, @Param("accountNum") String accountNum);

    @Transactional
    @Modifying
    @Query("UPDATE SavingsAccount a  set a.rate=:rate where a.accountNum=:accountNum")
    public void ChangeRate(@Param("rate") double rate, @Param("accountNum") String accountNum);

}
