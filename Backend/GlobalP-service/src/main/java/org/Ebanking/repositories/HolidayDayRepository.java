package org.Ebanking.repositories;

import org.Ebanking.entities.HolidayDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Date;

@CrossOrigin("*")
@RepositoryRestResource(path = "api")
public interface HolidayDayRepository extends JpaRepository<HolidayDay, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE HolidayDay d  set d.name=:name, d.date=:date where d.id=:id")
    public void editHoliday(@Param( "name" ) String name, @Param( "date" ) Date date, @Param ( "id" )  Long id);

}
