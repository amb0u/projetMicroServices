package org.Ebanking.transactionservice.serviceJwt;


import org.Ebanking.transactionservice.classes.MonthResume;
import org.Ebanking.transactionservice.entities.Virement;
import org.Ebanking.transactionservice.security.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
