package org.Ebanking.Adminservice.global;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface CurrentJWT {

    public String getJwt(HttpServletRequest request);
    public String getJWtWithoutBearer( HttpServletRequest request);
    public Cookie setCookie( String endPoint, String jwt);
    public Cookie resetCookie( String endPoint);
}
