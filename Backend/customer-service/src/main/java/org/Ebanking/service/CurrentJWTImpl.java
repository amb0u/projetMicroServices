package org.Ebanking.service;

import org.Ebanking.security.SecurityConstants;

import javax.servlet.http.HttpServletRequest;


public class CurrentJWTImpl implements CurrentJWT {

        @Override
        public String getJWT(HttpServletRequest request) {
            String jwtToken = request.getHeader( SecurityConstants.HEADER_STRING);

            if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                return null;
            }

            return jwtToken;
        }
}
