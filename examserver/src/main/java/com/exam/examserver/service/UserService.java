package com.exam.examserver.service;

import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;

import java.util.Set;

public interface UserService {

    public User createUser(User user , Set<UserRole> userRoles) throws Exception;

    User getUser(String username);

    void deleteUser(Long id);
}
