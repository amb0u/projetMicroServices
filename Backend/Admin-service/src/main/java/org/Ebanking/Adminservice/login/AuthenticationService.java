package org.Ebanking.Adminservice.login;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AUTH-SERVICE")
public interface AuthenticationService {

    @RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json")
    public ResponseEntity sendLogin(UserLogin userLogin);


}
