package org.Ebanking.repositories;

import org.Ebanking.entities.ContratType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;

@CrossOrigin("*")
@RepositoryRestResource(path = "api")
public interface ContratTypeRepository extends JpaRepository<ContratType, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE ContratType c  set c.name=:name, c.description=:description where c.id=:id")
    public void editContrat(@Param( "name" ) String name, @Param( "description" ) String description, @Param ( "id" )  Long id);

}
