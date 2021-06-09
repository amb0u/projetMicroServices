package org.Ebanking.controllers;

import lombok.AllArgsConstructor;
import org.Ebanking.entities.ContratType;
import org.Ebanking.repositories.ContratTypeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class ContratController{
    private ContratTypeRepository contratTypeRepository;

    @RequestMapping(value = "/contrats" , method = RequestMethod.GET)
    public List<ContratType> getContrats() {
        return contratTypeRepository.findAll ();
    }

    @RequestMapping(value = "/contrats/{id}" , method = RequestMethod.GET)
    public ContratType getContratTypeById(@PathVariable(name = "id") Long id) {
        return contratTypeRepository.findById ( id ).get ();
    }

    @RequestMapping(value = "/contrats" , method = RequestMethod.POST)
    public ContratType saveContrat(@RequestBody ContratType contratType){
        ContratType newContratType = new ContratType ( null, contratType.getName (),contratType.getDescription ());
        return contratTypeRepository.save ( newContratType );
    }

    @RequestMapping(value = "/contrats/{id}" , method = RequestMethod.PUT)
    public void editContract(@PathVariable(name = "id") Long id , @RequestBody ContratType contratType){
        if (contratTypeRepository.existsById ( id )) {
            contratTypeRepository.editContrat ( contratType.getName (), contratType.getDescription (), id );
        }
    }

    @RequestMapping(value = "/contrats/{id}", method = RequestMethod.DELETE)
    public void deleteContrat (@PathVariable(name = "id") Long id ){
        contratTypeRepository.deleteById(id);
    }
}
