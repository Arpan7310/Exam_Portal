package com.exam;

import com.exam.enitity.Role;
import com.exam.enitity.User;
import com.exam.enitity.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication  {


    @Autowired
	private UserService userService;


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public  static void main(String[] args)  {
		SpringApplication.run(ExamserverApplication.class, args);

		ExamserverApplication c = new ExamserverApplication();

	}


//	@Override
//	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setFirstName("Arpan");
//		user.setLastName("Mukherjee");
//		user.setUsername("arpan7310");
//		user.setPassword(this.bCryptPasswordEncoder.encode("password"));
//		user.setEmail("abc@gmail.com");
//		user.setProfile("default.png");
//
//		Role role1= new Role();
//		role1.setRoleId(44L);
//		role1.setRoleName("ADMIN");
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//
//		UserRole userRole= new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//
// 	userRoleSet.add(userRole);
//
//		User user1 = this.userService.createUser(user,userRoleSet);
//		System.out.println(user1.getUsername());
//	}



}
