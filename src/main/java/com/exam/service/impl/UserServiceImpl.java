package com.exam.service.impl;


import com.exam.enitity.User;
import com.exam.enitity.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;




    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws  Exception {

     User local=   this.userRepository.findByUsername(user.getUsername());



     if(local!=null)
     {
         System.out.println("user is already there");
         throw new UserFoundException();
     }
     else  {

         for (UserRole ur:userRoles)
         {
             roleRepository.save(ur.getRole());
         }
     }

     user.getUserRoles().addAll(userRoles);
     local= this.userRepository.save(user);


        return local;
    }


    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }


    @Override
    public User updateUser(User user) throws  Exception {


        if(this.userRepository.findById(user.getId()).isPresent()){
            this.userRepository.save(user);

        }
        else
            throw new Exception("User is not present");

        return user;
    }



}
