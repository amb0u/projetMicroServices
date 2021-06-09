package org.Ebanking.accountService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.Ebanking.accountService.Repositories.SavingsAccountRepository;
import org.Ebanking.accountService.classes.CustomerService;
import org.Ebanking.accountService.classes.OfficerService;
import org.Ebanking.accountService.entities.SavingsAccount;
import org.Ebanking.accountService.serviceJwt.CurrentJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class SavingsAccountRestController{

    private SavingsAccountRepository savingsAccountRepository;
    private CustomerService customerService;
    private CurrentJWT currentJWT;
    private OfficerService officerService;

    //We don't need it
   /* @RequestMapping(value = "/savingsAccounts" , method = RequestMethod.GET)
    public Collection<SavingsAccount> getSavingsAccounts(){
        return  savingsAccountRepository.findAll ();
    }*/

    @RequestMapping(value = "/savingsAccounts/{id}" , method = RequestMethod.GET)
    public SavingsAccount getSavingsAccountById(@PathVariable(name = "id") Long id, HttpServletRequest request , HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        SavingsAccount savingsAccount = savingsAccountRepository.findById(id).get();
        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the owner to get his account infos.

            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == savingsAccount.getClientId()){
                response.setStatus (200 );
                return savingsAccount;
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the request that he want to get.
            Long clientId = savingsAccount.getClientId();

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                response.setStatus (200 );
                return savingsAccount;
            }
        }

        else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            response.setStatus (200 );
            return savingsAccount;
        }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/savingsAccounts/accountNum/{accountNum}" , method = RequestMethod.GET)
    public SavingsAccount getSavingsAccountByAccountNum(@PathVariable(name = "accountNum") String accountNum , HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        SavingsAccount savingsAccount = savingsAccountRepository.findSavingsAccountByAccountNum ( accountNum );
        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){  // Client must be the owner to get his account infos

            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == savingsAccount .getClientId()){
                response.setStatus (200 );
                return savingsAccount ;
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the account.
            Long clientId = savingsAccount .getClientId();

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                response.setStatus (200 );
                return savingsAccount ;
            }
        }

        else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            response.setStatus (200 );
            return savingsAccount ;
        }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/customer/savingsAccounts/{clientId}" , method = RequestMethod.GET)
    public SavingsAccount getSavingsAccountByClientId(@PathVariable(name = "clientId") Long clientId, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the owner to get his account infos.

            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == clientId){
                response.setStatus (200 );
                return  savingsAccountRepository.findSavingsAccountByClientId ( clientId );
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the account

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                response.setStatus (200 );
                return  savingsAccountRepository.findSavingsAccountByClientId ( clientId );
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            response.setStatus (200 );
            return  savingsAccountRepository.findSavingsAccountByClientId ( clientId );
        }

        response.setStatus ( 403 );
        return null;
    }


    @RequestMapping(value = "/savingsAccounts" , method = RequestMethod.POST)
    public boolean saveSavingsAccount(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        System.out.print("this is saving account");
        ObjectMapper obj = new ObjectMapper();
        SavingsAccount account = obj.readValue(payload,SavingsAccount.class);
        customerService.addCustomersSavingsAccount (currentJWT.getJWT ( request ), account );
        savingsAccountRepository.save ( account );
        response.setStatus(200);
        return true;
    }


    @RequestMapping(value = "/savingsAccounts/{id}" , method = RequestMethod.DELETE)
    public void deleteSavingsAccount(@PathVariable(name = "id") Long id, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        SavingsAccount savingsAccount = savingsAccountRepository.findById(id).get();
        if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the account to delete it
            Long clientId = savingsAccount.getClientId();

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                savingsAccountRepository.deleteById(id);
                response.setStatus ( 200 );
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            savingsAccountRepository.deleteById(id);
            response.setStatus (200 );
        }
        else response.setStatus ( 403 );
    }

    @RequestMapping(value = "/customer/savingsAccounts/{clientId}" , method = RequestMethod.DELETE)
    public void deleteSavingsAccountByClientId(@PathVariable(name = "clientId") Long clientId, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner to delete his account
            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                savingsAccountRepository.deleteSavingAccountByClientId ( clientId );
                response.setStatus ( 200 );
                return;
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            response.setStatus (200 );
            savingsAccountRepository.deleteSavingAccountByClientId ( clientId );
            return;
        }
        else response.setStatus ( 403 );
    }

}
