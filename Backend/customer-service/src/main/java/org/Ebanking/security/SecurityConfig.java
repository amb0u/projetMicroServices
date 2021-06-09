package org.Ebanking.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Let it empty to avoid the autoConfiguration which generate default Security password.
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().mvcMatchers ( HttpMethod.PUT,("/api/customers/**")).hasAnyAuthority (  "ADMIN","CLIENT","OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/customers/{^[\\d]$}").hasAnyAuthority (  "ADMIN","CLIENT","OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/customersByCin/**").hasAnyAuthority ( "ADMIN", "OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/customersByEmail/**").hasAnyAuthority ( "ADMIN", "OFFICER","CLIENT" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/customersByStatus/**").hasAnyAuthority ( "ADMIN", "OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/customersByContract/**").hasAnyAuthority ( "ADMIN", "OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/customersByAgency/**").hasAnyAuthority ( "ADMIN", "OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/CustomerIdByEmail/**").hasAuthority ( "CLIENT")

                                .mvcMatchers ( HttpMethod.GET,("/api/getMyId/**")).hasAnyAuthority (  "ADMIN","CLIENT","OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/customers").hasAuthority ( "ADMIN" )

                                .mvcMatchers ( HttpMethod.POST ,"/api/customers").hasAnyAuthority ( "ADMIN", "OFFICER" )
                                .mvcMatchers ( HttpMethod.POST ,"/api/customer/currentAccount").authenticated () //TODO need to look how to secure it as it comes just from backend !
                                .mvcMatchers ( HttpMethod.POST ,"/api/customer/savingsAccount").authenticated () //TODO need to look how to secure it as it comes just from backend !

                                .mvcMatchers ( HttpMethod.DELETE,("/api/customers/**")).hasAnyAuthority ( "ADMIN", "OFFICER")

                                .anyRequest().authenticated();

        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
