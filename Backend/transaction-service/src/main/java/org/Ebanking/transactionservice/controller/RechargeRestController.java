package org.Ebanking.transactionservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.Ebanking.transactionservice.classes.CustomerService;
import org.Ebanking.transactionservice.classes.MyProcessor;
import org.Ebanking.transactionservice.entities.Recharge;
import org.Ebanking.transactionservice.entities.Virement;
import org.Ebanking.transactionservice.repositories.RechargeRepository;
import org.Ebanking.transactionservice.serviceJwt.CurrentJWT;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
@EnableBinding(MyProcessor.class)
public class RechargeRestController {

    RechargeRepository rechargeRepository;
    CustomerService customerService;
    CurrentJWT currentJWT;

    MyProcessor processor;


    @GetMapping(value = "/recharges")
    public Collection<Recharge> getRecharge(HttpServletResponse response){
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

            if ( authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )) {
                  return rechargeRepository.findAll();
            }

            response.setStatus ( 403 );
            return null;
    }


    @RequestMapping(value = "/recharges/{id}" , method = RequestMethod.GET)
    public Recharge getRechargeById(@PathVariable(name = "id") Long id, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (!rechargeRepository.existsById ( id )){
            response.setStatus ( 204 );
            return null;
        }

        Recharge recharge = rechargeRepository.findById ( id ).get ();;

        if ( authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )) {
                return recharge;
        }

        else  if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

            Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

            if (recharge.getAccountNum ().equals ( accountNumOfUser )) {
                return recharge;
            }
            else {
                response.setStatus ( 403 );
                return null;
            }
        }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/account/recharges/{accountNum}" , method = RequestMethod.GET)
    public Collection<Recharge> getRechargeByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

            Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

            if (accountNum.equals ( accountNumOfUser )) {
                return  rechargeRepository.findRechargeByAccountNum(accountNum);
            }

            else {
                response.setStatus ( 403 );
                return null;
            }

        }

        response.setStatus ( 403 );
        return null;
    }


    @PostMapping(value = "/recharges")
    public boolean createRecharge(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

            Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

            ObjectMapper obj = new ObjectMapper();
            Recharge recharge = obj.readValue(payload,Recharge.class);


            if (recharge.getAccountNum ().equals ( accountNumOfUser )) {
                recharge.setTransactionDate ( new Date (  ) );
                processor.myOutput1().send(MessageBuilder.withPayload(recharge).build());
                return true;
            }
            else {
                response.setStatus ( 403 );
                return false;
            }

        }

        response.setStatus ( 403 );
        return false;

    }

    /** ****************** Needed in Front-End *********************/

    @RequestMapping(value = "/account/3LastRecharges/{accountNum}" , method = RequestMethod.GET)
    public Collection<Recharge> get3LastRechargesByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

            if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

                Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

                    if (accountNum.equals ( accountNumOfUser )) {
                                Collection<Recharge> recharges = rechargeRepository.findRechargeByAccountNum ( accountNum );
                                List<Recharge> ThreeLastVirements = new ArrayList<Recharge> ( 3 );
                                if (recharges.size () > 3){
                                    ThreeLastVirements.addAll ( recharges.stream ().skip ( recharges.size () - 3 ).collect( Collectors.toList()) );
                                    return ThreeLastVirements;
                                }
                                else {
                                    return recharges;
                                }
                    }
            }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/account/10LastRecharges/{accountNum}" , method = RequestMethod.GET)
    public Collection<Recharge> get10LastRechargesByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

            if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

                Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

                if (accountNum.equals ( accountNumOfUser )) {
                        Collection<Recharge> recharges = rechargeRepository.findRechargeByAccountNum ( accountNum );
                        List<Recharge> ThreeLastVirements = new ArrayList<Recharge> ( 10);
                        if (recharges.size () > 10){
                            ThreeLastVirements.addAll ( recharges.stream ().skip ( recharges.size () - 10 ).collect( Collectors.toList()) );
                            return ThreeLastVirements;
                        }
                        else {
                            return recharges;
                        }
                }
            }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/account/3LastMonthsRecharges/{accountNum}" , method = RequestMethod.GET)
    public Collection<Recharge> get3LastMonthsVirementsByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

            Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

            if (accountNum.equals ( accountNumOfUser )) {
                    Calendar LastDate = Calendar.getInstance();
                    LastDate.set(Calendar.HOUR_OF_DAY,0);
                    LastDate.set(Calendar.MINUTE,0);
                    LastDate.set(Calendar.SECOND,0);
                    LastDate.add(Calendar.DATE,-90);

                    Date startDate = LastDate.getTime();

                    return  rechargeRepository.findRechargeByTransactionDateBetween ( startDate,  new Date (  ) );

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
