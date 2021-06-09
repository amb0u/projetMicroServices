package com.bank.repositories;
import com.bank.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

@CrossOrigin("*")
@RepositoryRestResource(excerptProjection = Officer.class, path = "api")
public interface OfficerRepository  extends JpaRepository<Officer, Long> {

    public Collection<Officer> findOfficerByAgencyId(Long agencyId);
    public Collection<Officer> findOfficerByRole(String role);
    public Officer findOfficerByEmail(String email);

}
