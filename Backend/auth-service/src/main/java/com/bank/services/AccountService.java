package com.bank.services;

import com.bank.entities.Role;
import com.bank.entities.User;

public interface AccountService {
    public User saveUser(String userName , String password , String RoleName);
    public boolean updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword);
    public User changeTheActivatedBoolean(String userName);
    public Role save(Role role); //ajouter un role
    public User loadUserByUserName(String userName); //charger un user
    public void addRoleToUser(String userName, String roleName); //ajouter un role a un user
}
