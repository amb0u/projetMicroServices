package org.Ebanking.controllers;

import com.fasterxml.jackson.core.PrettyPrinter;
import lombok.AllArgsConstructor;
import org.Ebanking.entities.Currency;
import org.Ebanking.repositories.CurrencyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class CurrencyController {

    private CurrencyRepository currencyRepository;

    @RequestMapping(value = "/currency" , method = RequestMethod.GET)
    public Optional<Currency> getCurrency() {
        if (currencyRepository.count () != 0){
            return currencyRepository.findById ( 1L );
        }
        return null;
    }


    @RequestMapping(value = "/currency" , method = RequestMethod.POST)
    public Currency saveCurrency(@RequestBody Currency currency){
        if (currencyRepository.count () == 0) {
            Currency newCurrency = new Currency ( 1L, currency.getSymbol () );
            return currencyRepository.save ( newCurrency );
        }
        else{
            return null;
        }
    }

    @RequestMapping(value = "/currency" , method = RequestMethod.PUT)
    public void editCurrency( @RequestBody Currency currency){
            currencyRepository.editCurrency ( currency.getSymbol (), 1L );
    }

    @RequestMapping(value = "/currency", method = RequestMethod.DELETE)
    public void deleteCurrency (){
            currencyRepository.deleteById(1L);
    }
}
