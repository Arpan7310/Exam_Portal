package com.exam.service;

import com.exam.enitity.User;
import com.exam.enitity.UserRole;

import java.util.Set;

public interface UserService {

    //creating User
    public User createUser(User user, Set<UserRole> userRoles) throws  Exception;


    //get User

    public User getUser (String username);


    public void deleteUser(Long userId);

    public User updateUser(User user) throws Exception;


}
