package org.Ebanking.accountService.serviceJwt;

import javax.servlet.http.HttpServletRequest;


public interface CurrentJWT {


    public String getJWT(HttpServletRequest request);

}
