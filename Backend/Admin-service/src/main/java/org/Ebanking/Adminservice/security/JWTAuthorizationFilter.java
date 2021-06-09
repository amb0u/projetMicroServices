package org.Ebanking.Adminservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";
    private static final String LOGINUSER = "/loginUser";
    private static final String CSS = ".css";
    private static final String FAVICON = "/favicon.ico";
    private static final String JS = ".js";
    private static final String webjars = "webjars";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Headers","Origin , Accept , X-Requested-With , Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
        response.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin, Access-Control-Allow-Credentials,authorization");

        if(request.getMethod().equals("OPTIONS")){
            response.setStatus( HttpServletResponse.SC_OK);
        }
        else {

            if ( request.getRequestURI ().equals ( LOGIN ) || request.getRequestURI ().equals ( LOGINUSER ) || request.getRequestURI ().equals ( FAVICON ) ||
                            request.getRequestURI ().equals ( LOGOUT )  || request.getRequestURI ().contains ( CSS )  || request.getRequestURI ().contains ( JS )
                                        || request.getRequestURI ().contains ( webjars )) {

                                  filterChain.doFilter ( request, response );
                                return;
            }


            if ( ! Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(SecurityConstants.Cookie_Name) && cookie.getMaxAge ()!=0).map( Cookie::getValue).findFirst ().isPresent ()) {

                    response.sendRedirect ( "/login" );
                    return;
            }

            String jwtToken1 = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(SecurityConstants.Cookie_Name) && cookie.getMaxAge ()!=0).map( Cookie::getValue).findFirst ().get ();


            String jwtToken = SecurityConstants.TOKEN_PREFIX + jwtToken1;

            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET)).build();
            String jwt = jwtToken.substring(SecurityConstants.TOKEN_PREFIX.length());
            DecodedJWT decodedJWT = verifier.verify(jwt);
            String username = decodedJWT.getSubject();
            List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(rn -> {
                authorities.add(new SimpleGrantedAuthority (rn));
            });
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken (username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(user);
            filterChain.doFilter(request, response);
        }

    }
}
