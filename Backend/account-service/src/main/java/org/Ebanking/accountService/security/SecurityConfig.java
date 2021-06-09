package org.Ebanking.accountService.security;

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

    /******************************************** CurrentAccounts ***********************************************/
                http.authorizeRequests().mvcMatchers ( HttpMethod.POST,("/api/currentAccounts")).hasAnyAuthority("OFFICER","ADMIN")
                                        .mvcMatchers ( HttpMethod.GET ,"/api/currentAccounts").hasAnyAuthority (  "OFFICER" ,"ADMIN")
                                        .mvcMatchers ( HttpMethod.GET ,"/api/currentAccounts/{^[\\d]$}").hasAnyAuthority (  "ADMIN","OFFICER","CLIENT" )
                                        .mvcMatchers ( HttpMethod.DELETE,("/api/currentAccounts/{^[\\d]$}")).hasAnyAuthority("OFFICER","ADMIN")
                                        .mvcMatchers ( HttpMethod.DELETE,("/api/customer/currentAccounts/{^[\\d]$}")).hasAnyAuthority("OFFICER","ADMIN")

    /******************************************** Cards Of currentAccounts ***********************************************/

                    .mvcMatchers ( HttpMethod.GET ,"/api/cards/{^[\\d]$}").hasAuthority ( "CLIENT" )
                    .mvcMatchers ( HttpMethod.POST ,"/api/cards/MasterCard").hasAnyAuthority (  "ADMIN","OFFICER" )
                    .mvcMatchers ( HttpMethod.POST,("/api/cards/visa")).hasAnyAuthority("OFFICER","ADMIN")
                        .mvcMatchers ( HttpMethod.PUT ,"/api/cards/OpposeCard/{^[\\d]$}").hasAnyAuthority (  "ADMIN","OFFICER" )
    /******************************************** SavingsAccounts ***********************************************/

                        .mvcMatchers ( HttpMethod.POST,("/api/savingsAccounts")).hasAnyAuthority("OFFICER","ADMIN")
                                       .mvcMatchers ( HttpMethod.GET ,"/api/savingsAccounts").hasAnyAuthority("OFFICER","ADMIN")
                                       .mvcMatchers ( HttpMethod.GET ,"/api/savingsAccounts/{^[\\d]$}").hasAnyAuthority (  "ADMIN","OFFICER","CLIENT" )
                                       .mvcMatchers ( HttpMethod.DELETE,("/api/savingsAccounts/{^[\\d]$}")).hasAnyAuthority("OFFICER","ADMIN")
                                       .mvcMatchers ( HttpMethod.DELETE,("/api/customer/savingsAccounts/{^[\\d]$}")).hasAnyAuthority("OFFICER","ADMIN")

                                        .anyRequest().authenticated();

        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
