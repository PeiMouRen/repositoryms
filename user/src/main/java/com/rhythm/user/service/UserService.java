package com.rhythm.user.service;

import com.rhythm.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    public Set<String> getRoles(String username) {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("teacher");
        roles.add("student");
        return roles;
    }

    public Set<String> getPermissions(String username) {
        Set<String> permissions = new HashSet<>();
        permissions.add("user:*");
        permissions.add("student:*");
        return permissions;
    }

    public User getByUsername(String username) {
        User user = new User();
        user.setId(1);
        user.setUsername(username);
        user.setPassword("1234");
        return user;
    }
}
