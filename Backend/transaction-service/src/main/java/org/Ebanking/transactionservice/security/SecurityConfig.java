package org.Ebanking.transactionservice.security;

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

                    /** -------------------------- Virements ----------------------------*/
        http.authorizeRequests().mvcMatchers ( HttpMethod.GET,("/api/virements")).hasAuthority("ADMIN")
                                .mvcMatchers ( HttpMethod.GET ,"/api/account/virements/{accountNum}").hasAuthority ( "CLIENT")
                                .mvcMatchers ( HttpMethod.GET ,"/api/virements/{^[\\d]$}").hasAnyAuthority (  "ADMIN","CLIENT" )
                                .mvcMatchers ( HttpMethod.GET ,"/api/account/3LastVirements/{accountNum}").hasAuthority ( "CLIENT")
                                .mvcMatchers ( HttpMethod.GET ,"/api/account/10LastVirements/{accountNum}").hasAuthority ( "CLIENT")
                                .mvcMatchers ( HttpMethod.GET ,"/api/account/3LastMonthsVirements/{accountNum}").hasAuthority ( "CLIENT")
                                .mvcMatchers ( HttpMethod.GET ,"/api/account/incomeOutcome/{accountNum}").hasAuthority ( "CLIENT")
                                .mvcMatchers ( HttpMethod.POST,("/api/virements")).hasAuthority("CLIENT")

                     /** -------------------------- Recharges ---------------------------*/
                               .mvcMatchers ( HttpMethod.GET,("/api/recharges")).hasAuthority("ADMIN")
                               .mvcMatchers ( HttpMethod.GET ,"/api/account/recharges/{accountNum}").hasAuthority ( "CLIENT")
                               .mvcMatchers ( HttpMethod.GET ,"/api/recharges/{^[\\d]$}").hasAnyAuthority (  "ADMIN","CLIENT" )
                               .mvcMatchers ( HttpMethod.GET ,"/api/account/3LastRecharges/**").hasAuthority ( "CLIENT")
                               .mvcMatchers ( HttpMethod.GET ,"/api/account/10LastRecharges/**").hasAuthority ( "CLIENT")
                               .mvcMatchers ( HttpMethod.GET ,"/api/account/3LastMonthsRecharges/**").hasAuthority ( "CLIENT")
                               .mvcMatchers ( HttpMethod.POST,("/api/recharges")).hasAuthority("CLIENT")

                                .anyRequest().authenticated();

        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
