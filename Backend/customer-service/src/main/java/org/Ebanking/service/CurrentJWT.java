package org.Ebanking.service;

import org.Ebanking.security.SecurityConstants;

import javax.servlet.http.HttpServletRequest;



public interface CurrentJWT {


    public String getJWT(HttpServletRequest request);

}
