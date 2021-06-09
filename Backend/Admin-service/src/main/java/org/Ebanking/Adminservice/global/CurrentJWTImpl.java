package org.Ebanking.Adminservice.global;

import lombok.NoArgsConstructor;
import org.Ebanking.Adminservice.security.SecurityConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@NoArgsConstructor
public class CurrentJWTImpl implements CurrentJWT {
    @Override
    public String getJwt(HttpServletRequest request) {
        String jwtToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals( SecurityConstants.Cookie_Name) && cookie.getMaxAge ()!=0).map( Cookie::getValue).findFirst ().get ();

        return SecurityConstants.TOKEN_PREFIX + jwtToken;
    }

    @Override
    public String getJWtWithoutBearer(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals( SecurityConstants.Cookie_Name) && cookie.getMaxAge ()!=0).map( Cookie::getValue).findFirst ().get ();
    }

    @Override
    public Cookie setCookie(String endPoint, String jwt) {
        Cookie cookie = new Cookie ( SecurityConstants.Cookie_Name, jwt );
        cookie.setPath ( endPoint );
        cookie.setMaxAge ( Integer.MAX_VALUE );
        return cookie;
    }

    @Override
    public Cookie resetCookie(String endPoint) {
        Cookie cookie = new Cookie ( SecurityConstants.Cookie_Name, null );
        cookie.setPath ( endPoint );
        cookie.setMaxAge ( 0 );
        return cookie;
    }
}
