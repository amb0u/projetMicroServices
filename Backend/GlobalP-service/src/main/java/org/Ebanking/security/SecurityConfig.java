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
        http.authorizeRequests().mvcMatchers ( HttpMethod.POST,("/api/currency")).hasAuthority("ADMIN") // currency
                                .mvcMatchers ( HttpMethod.PUT ,("/api/currency")).hasAuthority("ADMIN") // currency
                                .mvcMatchers ( HttpMethod.DELETE ,("/api/currency")).hasAuthority("ADMIN") // currency

                                .mvcMatchers ( HttpMethod.GET,("/api/status/**")).hasAnyAuthority ( "ADMIN", "OFFICER")
                                .mvcMatchers ( HttpMethod.POST,("/api/status")).hasAuthority("ADMIN") // Status
                                .mvcMatchers ( HttpMethod.PUT ,("/api/status/**")).hasAuthority("ADMIN") // Status
                                .mvcMatchers ( HttpMethod.DELETE ,("/api/status/**")).hasAuthority("ADMIN") // Status

                                .mvcMatchers ( HttpMethod.GET,("/api/roles/**")).hasAnyAuthority ( "ADMIN", "OFFICER") // roles
                                .mvcMatchers ( HttpMethod.POST,("/api/roles")).hasAuthority("ADMIN") // roles
                                .mvcMatchers ( HttpMethod.PUT ,("/api/roles/**")).hasAuthority("ADMIN") // roles
                                .mvcMatchers ( HttpMethod.DELETE ,("/api/roles/**")).hasAuthority("ADMIN") // roles

                                .mvcMatchers ( HttpMethod.GET,("/api/contrats/**")).hasAnyAuthority ( "ADMIN", "OFFICER") // contrats
                                .mvcMatchers ( HttpMethod.POST,("/api/contrats")).hasAuthority("ADMIN") // contrats
                                .mvcMatchers ( HttpMethod.PUT ,("/api/contrats/**")).hasAuthority("ADMIN") // contrats
                                .mvcMatchers ( HttpMethod.DELETE ,("/api/contrats/**")).hasAuthority("ADMIN") // contrats

                                .mvcMatchers ( HttpMethod.POST,("/api/holidays")).hasAuthority("ADMIN") // holidays
                                .mvcMatchers ( HttpMethod.PUT ,("/api/holidays/**")).hasAuthority("ADMIN") // holidays
                                .mvcMatchers ( HttpMethod.DELETE ,("/api/holidays/**")).hasAuthority("ADMIN") // holidays

                                .anyRequest().authenticated();
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
