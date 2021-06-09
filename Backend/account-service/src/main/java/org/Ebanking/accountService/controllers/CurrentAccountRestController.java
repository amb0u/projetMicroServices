package org.Ebanking.accountService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.Ebanking.accountService.Repositories.CardRepository;
import org.Ebanking.accountService.classes.CustomerService;
import org.Ebanking.accountService.classes.OfficerService;
import org.Ebanking.accountService.entities.Card;
import org.Ebanking.accountService.entities.CurrentAccount;
import org.Ebanking.accountService.Repositories.CurrentAccountRepository;
import org.Ebanking.accountService.serviceJwt.CurrentJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class CurrentAccountRestController {

    private final CurrentAccountRepository currentAccountRepository;
    private final CustomerService customerService;
    private CurrentJWT currentJWT;
    private OfficerService officerService;
    private CardRepository cardRepository;


    @RequestMapping(value = "/currentAccounts/{id}" , method = RequestMethod.GET)
    public CurrentAccount getCurrentAccountById(@PathVariable(name = "id") Long id, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        CurrentAccount currentAccount = currentAccountRepository.findById(id).get();
        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the owner to get his account infos.

            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == currentAccount.getClientId()){
                response.setStatus ( 200 );
                return currentAccount;
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the request that he want to get.
            Long clientId = currentAccount.getClientId();

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                response.setStatus ( 200 );
                return currentAccount;
            }
        }

        else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            response.setStatus ( 200 );
            return currentAccount;
        }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/currentAccounts/accountNum/{accountNum}" , method = RequestMethod.GET)
    public CurrentAccount getCurrentAccountByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        CurrentAccount currentAccount = currentAccountRepository.findCurrentAccountByAccountNum ( accountNum );
        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){  // Client must be the owner to get his account infos

            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == currentAccount.getClientId()){
                response.setStatus ( 200 );
                return currentAccount;
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the account
            Long clientId = currentAccount.getClientId();

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                response.setStatus ( 200 );
                return currentAccount;
            }
        }

        else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            response.setStatus ( 200 );
            return currentAccount;
        }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/customer/currentAccounts/{clientId}" , method = RequestMethod.GET)
    public CurrentAccount getCurrentAccountByClientId(@PathVariable(name = "clientId") Long clientId, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){ // Client must be the owner to get his account infos.

            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == clientId){
                response.setStatus ( 200 );
                return currentAccountRepository.findCurrentAccountByClientId ( clientId );
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the account

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                response.setStatus ( 200 );
                return currentAccountRepository.findCurrentAccountByClientId ( clientId );
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            response.setStatus ( 200 );
            return currentAccountRepository.findCurrentAccountByClientId ( clientId );
        }

        response.setStatus ( 403 );
        return null;
    }


    @RequestMapping(value = "/currentAccounts" , method = RequestMethod.POST)
    public boolean saveCurrentAccount(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        CurrentAccount account = obj.readValue(payload,CurrentAccount.class);
        customerService.addCustomersCurrentAccount ( currentJWT.getJWT ( request ),account );
        currentAccountRepository.save(account);
        response.setStatus(200);
        return true;
    }

    @RequestMapping(value = "/currentAccounts/{id}" , method = RequestMethod.DELETE)
    public void deleteCurrentAccount(@PathVariable(name = "id") Long id, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        CurrentAccount currentAccount = currentAccountRepository.findById(id).get();
        if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner of the account to delete it
            Long clientId = currentAccount.getClientId();

            if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                cardRepository.deleteByClientId ( id );
                currentAccountRepository.deleteById(id);
                response.setStatus ( 200 );
            }
        }
        else if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            cardRepository.deleteByClientId ( id );
            currentAccountRepository.deleteById(id);
            response.setStatus ( 200 );
        }
        else{
            response.setStatus ( 403 );
        }
    }

    @RequestMapping(value = "/customer/currentAccounts/{clientId}" , method = RequestMethod.DELETE)
    public void deleteCurrentAccountByClientId(@PathVariable(name = "clientId") Long clientId, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        //if (authentication.getAuthorities ().toString ().equals ( "[OFFICER]" ) ) { // Officer must have the same agencyId as the owner to delete his account
            //if ( officerService.getOfficersAgencyIdByEmail (currentJWT.getJWT ( request ), authentication.getName () ) == customerService.getCustomerAgencyIdById(currentJWT.getJWT ( request ),clientId)){
                CurrentAccount c =currentAccountRepository.findCurrentAccountByClientId ( clientId );
                c.getCards ().clear ();
                currentAccountRepository.save ( c );
                Collection<Card> cards = cardRepository.findCardByClientId ( clientId );
                cards.forEach ( card->{
                        card.setCurrentAccount ( null );
                        cardRepository.save ( card );
                        cardRepository.deleteById ( card.getId () );
                } );

                currentAccountRepository.deleteCurrentAccountByClientId ( clientId );
                response.setStatus ( 200 );
                return;
           // }
        //}
        /*if (authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )){
            cardRepository.deleteByClientId ( clientId );
            currentAccountRepository.deleteCurrentAccountByClientId ( clientId );
            response.setStatus ( 200 );
            return;
        }
        else{
            response.setStatus ( 403 );
        }*/
    }


    // Need it in Transaction Service :

    @RequestMapping(value = "/currentAccounts/Balance/{accountNum}" , method = RequestMethod.GET)
    public Double getCurrentAccountBalanceByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        CurrentAccount currentAccount = currentAccountRepository.findCurrentAccountByAccountNum ( accountNum );

        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )){  // Client must be the owner to get his account infos

            if ( customerService.getCustomerIdByEmail(currentJWT.getJWT ( request ), authentication.getName () ) == currentAccount.getClientId()){
                response.setStatus ( 200 );
                return currentAccount.getAccountBalance ();
            }
            else {
                response.setStatus ( 403 );
                return null;
            }
        }

        response.setStatus ( 403 );
        return null;
    }

}
