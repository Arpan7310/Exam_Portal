package com.exam.Controller;

import com.exam.enitity.Role;
import com.exam.enitity.User;
import com.exam.enitity.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.service.UserService;
import com.exam.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {


    @Autowired
    private UserService userService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //create user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        //Set for list of user_roles
        Set<UserRole> roles= new HashSet<>();

        //Create a role for the user
        Role role =new Role();
        role.setRoleId(45L);
        role.setRoleName("normal");

        //Add user and role to the user_role object
        UserRole userRole= new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        //Add userRole object to the set roles;
        roles.add(userRole);

        //Add roles set to the user object
        user.setUserRoles(roles);

        return this.userService.createUser(user,roles);
    }


    @GetMapping("/{username}")

    public User getUser(@PathVariable("username") String username) {

       return this.userService.getUser(username);
    }

    @DeleteMapping("/{id}")

    public void deleteUser(@PathVariable("id") Long id) {
         this.userService.deleteUser(id);
    }





}
