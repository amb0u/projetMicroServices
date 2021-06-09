package org.Ebanking.transactionservice.services;

import org.Ebanking.transactionservice.entities.Virement;

public interface VirementService {

    public void listen(Virement virement) ;

}
