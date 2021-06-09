package org.Ebanking.controllers;

import lombok.AllArgsConstructor;
import org.Ebanking.entities.Role;
import org.Ebanking.repositories.RoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@AllArgsConstructor
public class RoleController {

    private RoleRepository roleRepository;

    @RequestMapping(value = "/roles" , method = RequestMethod.GET)
    public List<Role> getRoles() {
         return roleRepository.findAll ();
    }

    @RequestMapping(value = "/roles/{id}" , method = RequestMethod.GET)
    public Role getRoleById(@PathVariable(name = "id") Long id) {
        return roleRepository.findById ( id ).get ();
    }

    @RequestMapping(value = "/roles" , method = RequestMethod.POST)
    public Role saveRole(@RequestBody Role role){
        Role newRole = new Role ( null, role.getName (), role.getDescription ());
        return roleRepository.save ( newRole );
    }

    @RequestMapping(value = "/roles/{id}" , method = RequestMethod.PUT)
    public void editRole(@PathVariable(name = "id") Long id , @RequestBody Role role){
        if (roleRepository.existsById ( id )) {
            roleRepository.editRole ( role.getName (), role.getDescription (), id );
        }
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    public void deleteRole (@PathVariable(name = "id") Long id ){
            roleRepository.deleteById(id);
    }

}
