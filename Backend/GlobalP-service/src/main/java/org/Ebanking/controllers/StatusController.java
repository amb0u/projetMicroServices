package org.Ebanking.controllers;

import lombok.AllArgsConstructor;
import org.Ebanking.entities.Status;
import org.Ebanking.repositories.StatusRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class StatusController {

    private StatusRepository statusRepository;

    @RequestMapping(value = "/status" , method = RequestMethod.GET)
    public List<Status> getStatus() {
         return statusRepository.findAll ();
    }

    @RequestMapping(value = "/status/{id}" , method = RequestMethod.GET)
    public Status getStatusById(@PathVariable(name = "id") Long id) {
        return statusRepository.findById ( id ).get ();
    }

    @RequestMapping(value = "/status" , method = RequestMethod.POST)
    public Status saveStatus(@RequestBody Status status){
            Status newStatus = new Status (null, status.getName (), status.getDescription () );
            return statusRepository.save ( newStatus );
    }

    @RequestMapping(value = "/status/{id}" , method = RequestMethod.PUT)
    public void editStatus(@PathVariable(name = "id") Long id , @RequestBody Status status){
        if (statusRepository.existsById ( id )) {
            statusRepository.editStatus ( status.getName (), status.getDescription (), id );
        }
    }


    @RequestMapping(value = "/status/{id}", method = RequestMethod.DELETE)
    public void deleteStatus (@PathVariable(name = "id") Long id ){
        statusRepository.deleteById(id);
    }

}
