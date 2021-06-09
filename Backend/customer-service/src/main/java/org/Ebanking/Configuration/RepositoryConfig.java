package org.Ebanking.Configuration;

import org.Ebanking.repositories.CustomerProjection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getProjectionConfiguration()
                .addProjection( CustomerProjection.class);
        //add.addProjection(..) whenever u add a projectionr to a class.
    }

}
