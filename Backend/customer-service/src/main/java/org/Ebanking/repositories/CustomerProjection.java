package org.Ebanking.repositories;

import org.Ebanking.entities.Customer;
import org.springframework.data.rest.core.config.Projection;


@Projection( name = "CustomerAccounts", types = { Customer.class })
public interface CustomerProjection {
     String getFirstName();
     String getLastName();
     String getCin();
}
