package com.bank.services;

import com.bank.entities.Role;
import com.bank.entities.User;
import com.bank.repositories.RoleRepository;
import com.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {
     @Autowired
    private UserRepository userRepository;
     @Autowired
    private RoleRepository roleRepository;
     @Autowired
     private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
    public User saveUser(String userName, String password, String RoleName) {
        User user = userRepository.findByUserName(userName);
        if(user!=null) throw new RuntimeException(("User already exists"));
        User user1= new User();
        user1.setId ( null );
        user1.setUserName(userName);
        user1.setActivated(true);
        user1.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user1);
        addRoleToUser(userName,RoleName);
        return user1;
    }

    @Override
    public boolean updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword) {
        User user = userRepository.findByUserName(userName);
        if(user == null) throw new RuntimeException(("User doesn't exists !"));
        System.out.print(oldPassword );
        if ( BCrypt.checkpw ( oldPassword, user.getPassword () ) && newPassword.equals ( confirmPassword ) ) {
            System.out.print("here");
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User changeTheActivatedBoolean(String userName) {
            User user = userRepository.findByUserName(userName);
            if(user == null) throw new RuntimeException(("User doesn't exists !"));
            if ( user.isActivated () ){
                user.setActivated ( false );
            }else {
                user.setActivated ( true );
            }
           return userRepository.save ( user );

    }


    @Override
  public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User loadUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByRoleName(roleName);
        user.setRole ( role );
    }

}
