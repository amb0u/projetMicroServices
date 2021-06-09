package org.Ebanking.agencyservice.repositories;

import org.Ebanking.agencyservice.entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

}
