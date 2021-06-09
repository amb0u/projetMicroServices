package org.Ebanking.repositories;

import org.Ebanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Collection;

@CrossOrigin("*")
@RepositoryRestResource(excerptProjection = Customer.class, path = "api")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Collection<Customer> findCustomerByCinContains(@Param ("cin") String  cin);

    public Collection<Customer> findCustomerByCinContainsAndAgencyId(@Param ("cin") String  cin, Long agencyId);

    public Collection<Customer> findCustomerByStatus(String Status);


    public Collection<Customer> findCustomerByStatusAndAgencyId(String Status, Long agencyId);

    public Collection<Customer> findCustomerByContract(String contract);

    public Collection<Customer> findCustomerByContractAndAgencyId(String contract, Long agencyId);

    public Collection<Customer> findCustomerByAgencyId(Long agencyId);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c  set c.currentAccountNum=:currentAccountNum where c.id=:id")
    public void AddCurrentAccountNumber(@Param ( "currentAccountNum" ) Long currentAccountNum, @Param ( "id" )  Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c  set c.savingsAccountNum=:savingsAccountNum where c.id=:id")
    public void AddSavingsAccountNumber(@Param ( "savingsAccountNum" ) Long savingsAccountNum, @Param ( "id" )  Long id);


    public Customer findCustomerByEmail( String email);

}
