package com.exam.examserver.service;

import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.repo.RoleRepository;
import com.exam.examserver.repo.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private RoleRepository roleRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //create users
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User newUser = new User();
        User local = this.userRepository.findByUsername(user.getUsername());
        if (local!=null){
            log.info("user is already exists..");
            throw new Exception("User is already present!!") ;
        }else {
            //create user
            for(UserRole ur:userRoles){
                this.roleRepository.save(ur.getRole());
            }
             user.getUsersRole().addAll(userRoles);
             user.setProfile("default.png");
             user.setPassword(passwordEncoder.encode(user.getPassword()));
             newUser =  this.userRepository.save(user);
        }
        return newUser;
    }

    @Override
    public User getUser(String username) {
        User users = this.userRepository.findByUsername(username);
        return users;
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}
