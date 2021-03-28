package com.rhythm.user.service;

import com.rhythm.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserService {

    public Set<String> getRoles(String username) {
        Set<String> roles = new HashSet<>();
        if ("admin".equals(username)) {
            System.out.println("username=" + username + ", roles=admin");
            roles.add("admin");
        } else {
            System.out.println("username=" + username + ", roles=teacher");
            roles.add("teacher");
        }
        return roles;
    }

    public Set<String> getPermissions(String username) {
        Set<String> permissions = new HashSet<>();
        if ("admin".equals(username)) {
            System.out.println("username=" + username + ", permissions=user:*");
            permissions.add("user:*");
        } else {
            System.out.println("username=" + username + ", permissions=student:*");
            permissions.add("student:*");
        }
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
