package com.bank.security;

import com.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**").permitAll()
                                .mvcMatchers ( HttpMethod.PUT ,"/users/customers/**").hasAuthority ( "CLIENT" )// Customers
                                .mvcMatchers ( HttpMethod.PUT ,"/users/customerByEmail/**").hasAuthority ( "CLIENT" )
                                .mvcMatchers ( HttpMethod.GET ,"/users/customers/deactivate/{^[\\d]$}").hasAnyAuthority ( "ADMIN", "OFFICER" )  // Customers
                                .mvcMatchers ( HttpMethod.GET ,"/users/customers/deactivateByEmail/**").hasAnyAuthority ( "ADMIN", "OFFICER" )  // Customers
                                .mvcMatchers ( HttpMethod.POST,"/users/customers").hasAnyAuthority ( "ADMIN", "OFFICER" )                      // Customers
                                .mvcMatchers ( HttpMethod.DELETE ,"/users/customers/**").hasAnyAuthority ( "ADMIN", "OFFICER" )              // Customers
                                .mvcMatchers ( HttpMethod.GET ,"/users/customers").hasAuthority("ADMIN")  // Customers


                                .mvcMatchers ( HttpMethod.PUT ,"/users/officers/**").hasAuthority ( "OFFICER" )         // Officers
                                .mvcMatchers ( HttpMethod.GET ,"/users/officers/**").hasAuthority("ADMIN")             // Officers
                                .mvcMatchers ( HttpMethod.POST,"/users/officers").hasAuthority ( "ADMIN")             // Officers
                                .mvcMatchers ( HttpMethod.DELETE ,"/users/officers/**").hasAuthority ( "ADMIN" )     // Officers


                                .antMatchers( "/users/admins/**" ).hasAuthority("ADMIN");   // Admins

        http.authorizeRequests().anyRequest().authenticated();


        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), userRepository ));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
