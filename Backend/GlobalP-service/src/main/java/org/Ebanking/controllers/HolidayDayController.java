package org.Ebanking.controllers;

import lombok.AllArgsConstructor;
import org.Ebanking.entities.HolidayDay;
import org.Ebanking.repositories.HolidayDayRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class HolidayDayController {

    private HolidayDayRepository holidayDayRepository;

    @RequestMapping(value = "/holidays" , method = RequestMethod.GET)
    public List<HolidayDay> getHolidays() {
        return holidayDayRepository.findAll ();
    }

    @RequestMapping(value = "/holidays/{id}" , method = RequestMethod.GET)
    public HolidayDay getHolidayById(@PathVariable(name = "id") Long id) {
        return holidayDayRepository.findById ( id ).get ();
    }

    @RequestMapping(value = "/holidays" , method = RequestMethod.POST)
    public HolidayDay saveHoliday(@RequestBody HolidayDay holiday){
        HolidayDay newHoliday = new HolidayDay ( null,holiday.getName (), holiday.getDate () );
        return holidayDayRepository.save ( newHoliday );
    }

    @RequestMapping(value = "/holidays/{id}" , method = RequestMethod.PUT)
    public void editHoliday(@PathVariable(name = "id") Long id , @RequestBody HolidayDay holiday){
        if (holidayDayRepository.existsById ( id )) {
            holidayDayRepository.editHoliday ( holiday.getName (),holiday.getDate (), id );
        }
    }

    @RequestMapping(value = "/holidays/{id}", method = RequestMethod.DELETE)
    public void deleteHoliday (@PathVariable(name = "id") Long id ){
        holidayDayRepository.deleteById(id);
    }

}
