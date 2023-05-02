package com.exam.examserver;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
@Log4j2
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService ;
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("server Start");

//		User user = new User();
//		user.setUsername("Praveen26");
//		user.setFirstname("Praveen");
//		user.setLastname("Pandey");
//		user.setPassword("12345");
//		user.setEmail("praveenpandey.uttarakhand@gmail.com");
//		user.setPhoneNumber("8477969434");
//		user.setProfile("default.png");
//
//
//		Role role1 = new Role();
//		role1.setRoleName("ADMIN");
//		role1.setRoleId(44L);
//
//		Set<UserRole> userRoles = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//
//		userRoles.add(userRole);
//
//		User user1 = this.userService.createUser(user, userRoles);
//		log.info("here is the username {}" ,  user.getUsername() );
	}
}
