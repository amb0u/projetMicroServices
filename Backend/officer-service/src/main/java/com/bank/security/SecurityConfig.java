package com.bank.security;

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

        http.authorizeRequests().mvcMatchers ( HttpMethod.PUT,("/api/officers/**")).hasAnyAuthority (  "ADMIN","OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/officers/{^[\\d]$}").hasAnyAuthority (  "ADMIN","OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/officerByEmail/**").hasAnyAuthority (  "ADMIN","OFFICER" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/officerByRole/**").hasAuthority (  "ADMIN" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/officers").hasAuthority (  "ADMIN" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/officerByAgencyId/**").hasAuthority (  "ADMIN" )
                                .mvcMatchers ( HttpMethod.GET ,"/officerAgencyIdByEmail/**").hasAuthority (  "OFFICER" )
                                .mvcMatchers ( HttpMethod.GET,("/api/getMyId/**")).hasAnyAuthority (  "ADMIN","OFFICER" )
                                .mvcMatchers ( HttpMethod.POST,("/api/officers/**")).hasAuthority("ADMIN")
                                .mvcMatchers ( HttpMethod.DELETE,("/api/officers/**")).hasAuthority("ADMIN")

                                .anyRequest().authenticated();

        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
