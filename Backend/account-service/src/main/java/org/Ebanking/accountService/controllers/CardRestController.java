package org.Ebanking.accountService.controllers;

import lombok.AllArgsConstructor;
import org.Ebanking.accountService.Repositories.CardRepository;
import org.Ebanking.accountService.Repositories.CurrentAccountRepository;
import org.Ebanking.accountService.classes.CustomerService;
import org.Ebanking.accountService.entities.Card;
import org.Ebanking.accountService.entities.CurrentAccount;
import org.Ebanking.accountService.serviceJwt.CurrentJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class CardRestController {

    private CardRepository cardRepository;
    private  CurrentAccountRepository currentAccountRepository;
    private CustomerService customerService;
    private CurrentJWT currentJWT;

    private static final String MASTER_CARD = "MasterCard";
    private static final String VISA_CARD = "VISA";



    @RequestMapping(value = "/cards/{clientId}" , method = RequestMethod.GET)
    public Collection<Card> getCardsById(@PathVariable( name = "clientId") Long clientId, HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

            if (cardRepository.existsByClientId ( clientId )) {
                    Long idConnected = customerService.getCustomerIdByEmail ( currentJWT.getJWT ( request ), authentication.getName () );

                    if (idConnected == clientId) {
                         return cardRepository.findCardByClientId ( clientId );
                    }
            }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/cards/OpposeCard/{id}" , method = RequestMethod.PUT)
    public void opposeCard (@PathVariable(name = "id") Long id, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        if (cardRepository.existsById ( id )) {
            if (authentication.getAuthorities().toString().equals("[OFFICER]") || authentication.getAuthorities().toString().equals("[ADMIN]") ) { // Officer && admin
                    cardRepository.editOpposed(true, id);
                    response.setStatus(200);
            }
        }
        else response.setStatus ( 403 );
    }

    @RequestMapping(value = "/cards/MasterCard" , method = RequestMethod.POST)
    public Card createMasterCard(@RequestBody Card newCard, HttpServletResponse response) {

            Calendar todayDate = Calendar.getInstance ();
            todayDate.add ( Calendar.DATE, todayDate.get ( Calendar.YEAR ) + 2 );
            Date datedExpiration = todayDate.getTime ();
            System.out.println ("Date D'Expiration .. : "+datedExpiration );
            if (currentAccountRepository.existsByClientId ( newCard.getClientId () )) {
                    CurrentAccount associatedCurrentAccount = currentAccountRepository.findCurrentAccountByClientId ( newCard.getClientId () );
                    return cardRepository.save ( new Card ( null, MASTER_CARD, newCard.getCardNumber (), newCard.getSecretCode (), false, datedExpiration, newCard.getClientId (), associatedCurrentAccount ) );
            }

            response.setStatus ( 403 );
            return null;
    }

    @RequestMapping(value = "/cards/visa" , method = RequestMethod.POST)
    public Card createVisaCard(@RequestBody Card newCard, HttpServletResponse response) {

        Calendar todayDate = Calendar.getInstance ();
        todayDate.add ( Calendar.DATE, todayDate.get ( Calendar.YEAR ) + 2 );
        Date datedExpiration = todayDate.getTime ();
        System.out.println ("Date D'Expiration .. : "+datedExpiration );
        if (currentAccountRepository.existsByClientId ( newCard.getClientId () )) {
                CurrentAccount associatedCurrentAccount = currentAccountRepository.findCurrentAccountByClientId ( newCard.getClientId () );
                return cardRepository.save ( new Card(null, VISA_CARD, newCard.getCardNumber (), newCard.getSecretCode (),false, datedExpiration,newCard.getClientId (), associatedCurrentAccount) );
        }

        response.setStatus ( 403 );
        return null;
    }
}
