package org.Ebanking.Gatewayservice.Configuration;


import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {


    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator ( rdc,dlp );
    }



}




    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.setAllowedOrigins( Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    */


/** Configuration static vers les services !
 * C'à d à chaque service on doit ajouter son route ici.
 * Exemple récuprere les clients par http://localhost:8888/customers
 @Bean

 RouteLocator staticRoutes(RouteLocatorBuilder builder){
 return builder.routes()
 .route(r->r.path ("/customers/**" ).uri ( "lb://CUSTOMER-SERVICE" ).id ( "r1" ))
 .build ();
 }

 */

/**Ici de manière dynamique, mais il faut pour l'utiliser citer le nom de service d'abord
 * Pour avoir list des clients on utilise http://localhost:8888/CUSTOMER-SERVICE/customers
 */
