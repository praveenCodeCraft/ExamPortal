package com.exam.examserver.controller;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService ;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        log.info("user details is {}" , user);
        Set<UserRole> userRoles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        userRoles.add(userRole);

        return this.userService.createUser(user ,userRoles );
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long id){
        this.userService.deleteUser(id);
    }

}
