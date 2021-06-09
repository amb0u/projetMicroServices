package org.Ebanking.repositories;

import org.Ebanking.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;

@CrossOrigin("*")
@RepositoryRestResource(path = "api")
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Role r  set r.name=:name, r.description=:description where r.id=:id")
    public void editRole(@Param( "name" ) String name, @Param( "description" ) String description, @Param ( "id" )  Long id);

}
