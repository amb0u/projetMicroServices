package com.bank.controllers;


import com.bank.entities.UpdateUser;
import com.bank.entities.User;
import com.bank.repositories.RoleRepository;
import com.bank.repositories.UserRepository;
import com.bank.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class UsersController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AccountService accountService;
    private static final  String CLIENT_ROLE = "CLIENT";
    private static final  String OFFICER_ROLE = "OFFICER";
    private static final  String ADMIN_ROLE = "ADMIN";


/************************************************** Api des Customers ! **********************************************************/
            @RequestMapping(value = "/users/customers" , method = RequestMethod.GET)
            public List<User> getCustomer() {
                return userRepository.findAllByRole ( roleRepository.findByRoleName (CLIENT_ROLE) );
            }



            @RequestMapping(value = "/users/customers" , method = RequestMethod.POST)
            public User saveCustomer(@RequestBody User user){
                    return accountService.saveUser ( user.getUserName (), user.getPassword (), CLIENT_ROLE );
                }


            @RequestMapping(value = "/users/customers/deactivate/{id}" , method = RequestMethod.GET) // deactivate or activate a user !
            public User deactivateCustomer(@PathVariable( name = "id") Long id) {
                Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

                if (userRepository.existsByRoleAndId ( roleRepository.findByRoleName ( CLIENT_ROLE ), id )) {
                    User user = userRepository.findById ( id ).get ();
                    if (authentication != null ) {
                        return accountService.changeTheActivatedBoolean ( user.getUserName () );
                    }
                }
                return null;
            }

    @RequestMapping(value = "/users/customers/deactivateByEmail/{username}" , method = RequestMethod.GET) // deactivate or activate a user !
    public User deactivateCustomerByEmail(@PathVariable( name = "username") String username) {
        Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

        if (userRepository.existsByRoleAndUserName( roleRepository.findByRoleName ( CLIENT_ROLE ), username )) {
            User user = userRepository.findByUserName ( username );
            if (authentication != null ) {
                return accountService.changeTheActivatedBoolean ( user.getUserName () );
            }
        }
        return null;
    }

            @RequestMapping(value = "/users/customers/{id}" , method = RequestMethod.PUT)
            public boolean updateCustomer(@PathVariable( name = "id") Long id,@RequestBody UpdateUser userWithNewPassword) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (userRepository.existsByRoleAndId(roleRepository.findByRoleName(CLIENT_ROLE), id)) {
                    User user = userRepository.findById(id).get();
                    if (authentication != null && authentication.getName().equals(userWithNewPassword.getUserName())) {
                        return accountService.updateUserPassword(userWithNewPassword.getUserName(), userWithNewPassword.getPassword(), userWithNewPassword.getNewPassword(), userWithNewPassword.getConfirmPassword());
                    }
                }
                 return false;
            }

            @RequestMapping(value = "/users/customerByEmail/{username}" , method = RequestMethod.PUT)
            public boolean updateCustomerByEmail(@PathVariable( name = "username") String username ,@RequestBody UpdateUser userWithNewPassword) {
               Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

               if (userRepository.existsByRoleAndUserName(roleRepository.findByRoleName(CLIENT_ROLE), username)) {
                   if (authentication != null && authentication.getName().equals(userWithNewPassword.getUserName())) {
                      return accountService.updateUserPassword(userWithNewPassword.getUserName(), userWithNewPassword.getPassword(), userWithNewPassword.getNewPassword(), userWithNewPassword.getConfirmPassword());
                   }
               }
               return false;
            }

            @RequestMapping(value = "/users/customers/{id}", method = RequestMethod.DELETE)
            public void deleteCustomer(@PathVariable( name = "id") Long id){
                    if(userRepository.existsByRoleAndId ( roleRepository.findByRoleName ( CLIENT_ROLE ),id )){
                            userRepository.deleteById ( id );
                    }
            }

/************************************************ Api des Officers ! ********************************************************/
            @RequestMapping(value = "/users/officers" , method = RequestMethod.GET)
            public List<User> getOfficers() {
                return userRepository.findAllByRole ( roleRepository.findByRoleName (OFFICER_ROLE));
            }

            @RequestMapping(value = "/users/officers" , method = RequestMethod.POST)
            public User saveOfficer(@RequestBody User user){
                return accountService.saveUser ( user.getUserName (), user.getPassword (), OFFICER_ROLE );
            }


            @RequestMapping(value = "/users/officers/deactivate/{id}" , method = RequestMethod.GET) // deactivate or activate a user !
            public User deactivateOfficer(@PathVariable( name = "id") Long id) {
                Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

                if (userRepository.existsByRoleAndId ( roleRepository.findByRoleName ( OFFICER_ROLE ), id )) {
                    User officer = userRepository.findById ( id ).get ();
                    if (authentication != null && authentication.getAuthorities ().contains ( ADMIN_ROLE )) {
                        return accountService.changeTheActivatedBoolean ( officer.getUserName () );
                    }
                }
                return null;
            }

            @RequestMapping(value = "/users/officers/{id}" , method = RequestMethod.PUT)
            public boolean updateOfficer(@PathVariable( name = "id") Long id,@RequestBody UpdateUser officerWithNewPassword) {
                Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

                if (userRepository.existsByRoleAndId ( roleRepository.findByRoleName ( OFFICER_ROLE ), id )) {
                    User user = userRepository.findById ( id ).get ();
                    if (authentication != null && authentication.getName ().equals ( officerWithNewPassword.getUserName () )) {
                        return accountService.updateUserPassword ( officerWithNewPassword.getUserName (), officerWithNewPassword.getPassword (), officerWithNewPassword.getNewPassword (), officerWithNewPassword.getConfirmPassword () );
                    }
                }

                return false;
            }


            @RequestMapping(value = "/users/officers/{id}", method = RequestMethod.DELETE)
            public void deleteOfficer(@PathVariable( name = "id") Long id){
                if(userRepository.existsByRoleAndId ( roleRepository.findByRoleName ( OFFICER_ROLE ),id )){
                    userRepository.deleteById ( id );
                }
            }

/******************************************************* Api des Admins ! ********************************************************/

            @RequestMapping(value = "/users/admins" , method = RequestMethod.GET)
            public List<User> getAdmins() {
                return userRepository.findAllByRole ( roleRepository.findByRoleName (ADMIN_ROLE) );
            }

            @RequestMapping(value = "/users/admins" , method = RequestMethod.POST)
            public User saveAdmin(@RequestBody User user){
                return accountService.saveUser ( user.getUserName (), user.getPassword (), ADMIN_ROLE );
            }


        @RequestMapping(value = "/users/admins/deactivate/{id}" , method = RequestMethod.GET) // deactivate or activate a user !
        public User deactivateAdmin(@PathVariable( name = "id") Long id) {
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

            if (userRepository.existsByRoleAndId ( roleRepository.findByRoleName ( ADMIN_ROLE ), id )) {
                User officer = userRepository.findById ( id ).get ();
                if (authentication != null && authentication.getAuthorities ().contains ( ADMIN_ROLE )) {
                    return accountService.changeTheActivatedBoolean ( officer.getUserName () );
                }
            }
            return null;
        }

        @RequestMapping(value = "/users/admins/{id}" , method = RequestMethod.PUT)
        public boolean updateAdmin(@PathVariable( name = "id") Long id,@RequestBody UpdateUser adminWithNewPassword) {
            Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();

            if (userRepository.existsByRoleAndId ( roleRepository.findByRoleName ( OFFICER_ROLE ), id )) {
                User user = userRepository.findById ( id ).get ();
                if (authentication != null && authentication.getName ().equals ( adminWithNewPassword.getUserName () )) {
                    return accountService.updateUserPassword ( adminWithNewPassword.getUserName (), adminWithNewPassword.getPassword (), adminWithNewPassword.getNewPassword (), adminWithNewPassword.getConfirmPassword () );
                }
            }

            return false;
        }


            @RequestMapping(value = "/users/admins/{id}", method = RequestMethod.DELETE)
            public void deleteAdmin(@PathVariable( name = "id") Long id){
                    if(userRepository.existsByRoleAndId ( roleRepository.findByRoleName ( ADMIN_ROLE ),id )){
                        userRepository.deleteById ( id );
                    }
            }

}


