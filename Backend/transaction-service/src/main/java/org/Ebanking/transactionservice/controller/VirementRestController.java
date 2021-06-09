package org.Ebanking.transactionservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.Ebanking.transactionservice.classes.AccountService;
import org.Ebanking.transactionservice.classes.CustomerService;
import org.Ebanking.transactionservice.classes.MonthResume;
import org.Ebanking.transactionservice.classes.MyProcessor;
import org.Ebanking.transactionservice.entities.Virement;
import org.Ebanking.transactionservice.repositories.VirementRepository;
import org.Ebanking.transactionservice.serviceJwt.CurrentJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.Double;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@EnableBinding(Source.class)
@Data
public class VirementRestController {

    @Autowired
    VirementRepository virementRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    CurrentJWT currentJWT;
    @Autowired
    AccountService accountService;

    @Autowired
    MyProcessor processor;


    Double outcome = 0d;


    @GetMapping(value = "/virements")
    public Collection<Virement> getVirement( HttpServletResponse response){
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

            if ( authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )) {
                     return virementRepository.findAll ();
            }

            response.setStatus ( 403 );
            return null;
    }

    @RequestMapping(value = "/virements/{id}" , method = RequestMethod.GET)
    public Virement getVirementById(@PathVariable(name = "id") Long id, HttpServletRequest request, HttpServletResponse response){
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

            if (!virementRepository.existsById ( id )){
                response.setStatus ( 204 );
                return null;
            }

            Virement virement = virementRepository.findById ( id ).get ();

            if ( authentication.getAuthorities ().toString ().equals ( "[ADMIN]" )) {
                 return virement;
            }

            else  if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

                Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

                if (virement.getAccountNum ().equals ( accountNumOfUser )) {
                    return virement;
                }
                else {
                    response.setStatus ( 403 );
                    return null;
                }
            }

            response.setStatus ( 403 );
            return null;
    }

    @RequestMapping(value = "/account/virements/{accountNum}" , method = RequestMethod.GET)
    public Collection<Virement> getVirementByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){

            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

             if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

                    Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

                    if (accountNum.equals ( accountNumOfUser )) {
                          return virementRepository.findVirementByAccountNum ( accountNum );
                    }

                    else {
                        response.setStatus ( 403 );
                        return null;
                    }

                }

                response.setStatus ( 403 );
                return null;
    }

    @PostMapping(value = "/virements")
    public boolean createVirement(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

            Long accountNumOfUser = customerService.getCustomerAccountNumByEmail ( currentJWT.getJWT ( request ), authentication.getName () );

            ObjectMapper obj = new ObjectMapper ();
            Virement virement = obj.readValue ( payload, Virement.class );

            if (virement.getAccountNum ().equals ( accountNumOfUser )) {

                virement.setTransactionDate ( new Date () );
                double BalanceBefore = accountService.getCurrentAccountBalanceByAccountNum ( currentJWT.getJWT ( request ), virement.getAccountNum () );
                double BalanceAfter = BalanceBefore - virement.getAmount ();

                if (BalanceAfter > 0) {
                    processor.myOutput2 ().send ( MessageBuilder.withPayload ( virement ).build () );
                    return true;
                } else {
                    response.setStatus ( 403 );
                    return false;
                }
            }
        }
        response.setStatus ( 403 );
        return false;
    }

    /** ****************** Needed in Front-End *********************/

    @RequestMapping(value = "/account/3LastVirements/{accountNum}" , method = RequestMethod.GET)
    public Collection<Virement> get3LastVirementsByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

            Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

            if (accountNum.equals ( accountNumOfUser )) {
                Collection<Virement> virements = virementRepository.findVirementByAccountNum ( accountNum );
                List<Virement> ThreeLastVirements = new ArrayList<Virement> ( 3 );
                if (virements.size () > 3){
                        ThreeLastVirements.addAll ( virements.stream ().skip ( virements.size () - 3 ).collect( Collectors.toList()) );
                        return ThreeLastVirements;
                 }
                else {
                    return virements;
                }
            }
        }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/account/10LastVirements/{accountNum}" , method = RequestMethod.GET)
    public Collection<Virement> get10LastVirementsByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if ( authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

            Long accountNumOfUser = customerService.getCustomerAccountNumByEmail (currentJWT.getJWT ( request )  ,authentication.getName ());

            if (accountNum.equals ( accountNumOfUser )) {
                Collection<Virement> virements = virementRepository.findVirementByAccountNum ( accountNum );
                List<Virement> ThreeLastVirements = new ArrayList<Virement> ( 10);
                if (virements.size () > 10){
                    ThreeLastVirements.addAll ( virements.stream ().skip ( virements.size () - 10 ).collect( Collectors.toList()) );
                    return ThreeLastVirements;
                }
                else {
                    return virements;
                }
            }
        }

        response.setStatus ( 403 );
        return null;
    }

    @RequestMapping(value = "/account/3LastMonthsVirements/{accountNum}" , method = RequestMethod.GET)
    public Collection<Virement> get3LastMonthsVirementsByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response){

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

                return  virementRepository.findVirementByTransactionDateBetween ( startDate,  new Date (  ) );

            }
            else {
                response.setStatus ( 403 );
                return null;
            }
        }

        response.setStatus ( 403 );
        return null;
    }



    @RequestMapping(value = "/account/incomeOutcome/{accountNum}" , method = RequestMethod.GET)
    public List<MonthResume> getIncomeOutcomeByAccountNum(@PathVariable(name = "accountNum") Long accountNum, HttpServletRequest request, HttpServletResponse response) {


        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (authentication.getAuthorities ().toString ().equals ( "[CLIENT]" )) {

            Long accountNumOfUser = customerService.getCustomerAccountNumByEmail ( currentJWT.getJWT ( request ), authentication.getName () );

            if (accountNum.equals ( accountNumOfUser )) {

                        List<MonthResume> monthResumes = new ArrayList<MonthResume> ( 3 );
                        Double difference;
                        Double income = null;


                        /************************************* This Month *************************************/

                        outcome = 0d;
                        income = 0d;
                        Calendar LastDate = Calendar.getInstance ();
                        LastDate.set ( Calendar.HOUR_OF_DAY, 0 );
                        LastDate.set ( Calendar.MINUTE, 0 );
                        LastDate.set ( Calendar.SECOND, 0 );
                        LastDate.add ( Calendar.DATE, -LastDate.get ( Calendar.DAY_OF_MONTH ) + 1 );

                        Date start = LastDate.getTime ();
                        Date end = new Date ();


                        List<Virement> virementsList = virementRepository.findVirementByTransactionDateBetweenOrderByTransactionDate ( start, end );

                        if (!virementsList.isEmpty ()) {


                            Virement firstOne = virementsList.get ( 0 );

                            if (virementsList.size () > 1) {
                                Virement LastOne = virementsList.get ( virementsList.size () - 1 );
                                difference = LastOne.getRemainingBalance () - firstOne.getRemainingBalance ();
                                income = difference > 0 ? difference : 0;
                                virementsList.forEach ( v -> {
                                    setOutcome ( getOutcome () + v.getAmount () );
                                } );
                            } else {
                                income = 0d;
                                setOutcome ( firstOne.getAmount () );
                            }


                            int month = LastDate.get ( Calendar.MONTH ) + 1;

                            Virement virement = virementRepository.findAll ().stream ().skip ( virementsList.size () - 1 ).collect ( Collectors.toList () ).get ( 0 );

                            monthResumes.add ( new MonthResume ( income, getOutcome (), virement.getRemainingBalance (), month, accountNum ) );

                        } else {

                            int month1 = LastDate.get ( Calendar.MONTH ) + 1;

                            monthResumes.add ( new MonthResume ( null, null, null, month1, accountNum ) );
                        }
                        /************************************* Last 1ed Month *************************************/

                        outcome = 0d;
                        income = 0d;

                        Calendar LastDate1 = Calendar.getInstance ();
                        LastDate1.add ( Calendar.DATE, -30 - LastDate1.get ( Calendar.DAY_OF_MONTH ) );


                        Date start1 = LastDate1.getTime ();
                        LastDate.add ( Calendar.DATE, -1 );
                        Date end1 = LastDate.getTime ();
                        List<Virement> virementsList1 = virementRepository.findVirementByTransactionDateBetweenOrderByTransactionDate ( start1, end1 );

                        if (!virementsList1.isEmpty ()) {

                            Virement firstOne = virementsList1.get ( 0 );

                            if (virementsList1.size () > 1) {
                                Virement LastOne = virementsList1.get ( virementsList1.size () - 1 );
                                difference = LastOne.getRemainingBalance () - firstOne.getRemainingBalance ();
                                income = difference > 0 ? difference : 0;
                                virementsList1.forEach ( v -> {
                                    setOutcome ( getOutcome () + v.getAmount () );
                                } );
                            } else {
                                income = 0d;
                                setOutcome ( firstOne.getAmount () );
                            }

                            int month1 = LastDate1.get ( Calendar.MONTH ) + 1;

                            Virement virement1 = virementRepository.findAll ().stream ().skip ( virementsList1.size () - 1 ).collect ( Collectors.toList () ).get ( 0 );

                            monthResumes.add ( new MonthResume ( income, getOutcome (), virement1.getRemainingBalance (), month1, accountNum ) );

                        } else {

                            int month1 = LastDate1.get ( Calendar.MONTH ) + 1;

                            monthResumes.add ( new MonthResume ( null, null, null, month1, accountNum ) );
                        }

                        /************************************ Last 2ed Month **************************************/

                        outcome = 0d;
                        income = 0d;

                        Calendar LastDate3 = Calendar.getInstance ();
                        LastDate3.add ( Calendar.DATE, -60 - LastDate3.get ( Calendar.DAY_OF_MONTH ) );


                        Date start3 = LastDate3.getTime ();
                        LastDate1.add ( Calendar.DATE, -1 );
                        Date end3 = LastDate1.getTime ();
                        List<Virement> virementsList3 = virementRepository.findVirementByTransactionDateBetweenOrderByTransactionDate ( start3, end3 );

                        if (!virementsList3.isEmpty ()) {

                            Virement firstOne = virementsList3.get ( 0 );

                            if (virementsList3.size () > 1) {
                                Virement LastOne = virementsList3.get ( virementsList3.size () - 1 );
                                difference = LastOne.getRemainingBalance () - firstOne.getRemainingBalance ();
                                income = difference > 0 ? difference : 0;
                                virementsList3.forEach ( v -> {
                                    setOutcome ( getOutcome () + v.getAmount () );
                                } );
                            } else {
                                income = 0d;
                                setOutcome ( firstOne.getAmount () );
                            }

                            int month3 = LastDate3.get ( Calendar.MONTH ) + 1;


                            Virement virement3 = virementRepository.findAll ().stream ().skip ( virementsList3.size () - 1 ).collect ( Collectors.toList () ).get ( 0 );

                            monthResumes.add ( new MonthResume ( income, getOutcome (), virement3.getRemainingBalance (), month3, accountNum ) );

                        } else {

                            int month3 = LastDate3.get ( Calendar.MONTH ) + 1;

                            monthResumes.add ( new MonthResume ( 0d, 0d, 0d, month3, accountNum ) );
                        }

                        return monthResumes;
            }
        }

        response.setStatus ( 403 );
        return null;
    }

}
