package com.Ebanking.Repositories;

import com.Ebanking.entities.Dstatus;
import com.Ebanking.entities.Demande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource(excerptProjection = Demande.class, path = "api")
public interface DemandeRepository extends JpaRepository<Demande , Long> {

    public List<Demande> findDemandeByClientId(Long clientId);
    public List<Demande> findDemandeByStatusAndAgencyId(Dstatus status, Long agencyId);
    public List<Demande> findDemandeByClientIdAndAgencyId(Long clientId , Long AgencyId);
    public Demande findDemandeByIdAndAgencyId(Long id , Long AgencyId);
    public List<Demande> findDemandeByAgencyId(Long agencyId);


    @Transactional
    @Modifying
    @Query("delete from Demande d where d.clientId=:clientId")
    public void deleteDemandeByClientId(@Param ( "clientId" ) Long clientId);

    @Transactional
    @Modifying
    @Query("UPDATE Demande d  set d.status=:status where d.id=:id")
    public void editStatus(@Param ( "status" ) Dstatus status, @Param ( "id" )  Long id);

}
