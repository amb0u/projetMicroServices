package org.Ebanking.transactionservice.Configuration;

import org.Ebanking.transactionservice.repositories.TransactionProjection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{
        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            config.getProjectionConfiguration()
                    .addProjection( TransactionProjection.class);
                //add.addProjection(..) whenever u add a projectionr to a class.
        }

}

