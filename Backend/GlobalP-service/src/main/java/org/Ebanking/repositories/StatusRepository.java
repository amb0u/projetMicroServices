package org.Ebanking.repositories;

import org.Ebanking.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;

@CrossOrigin("*")
@RepositoryRestResource(path = "api")
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Status r  set r.name=:name, r.description=:description where r.id=:id")
    public void editStatus(@Param( "name" ) String name, @Param( "description" ) String description, @Param ( "id" )  Long id);

}
