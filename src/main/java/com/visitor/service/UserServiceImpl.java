package com.visitor.service;

import com.visitor.controller.ApplicationController;
import com.visitor.domain.Role;
import com.visitor.domain.User;
import com.visitor.repository.RoleRepository;
import com.visitor.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationController.class);

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUsersByUsername(String username) {
        return userRepository.getUsersByUsername(username);
    }

    @Override
    public void saveUser(Map<String, String> request) {
        User user = new User();
        user.setFirstname(request.get("firstName"));
        user.setUsername(request.get("userName"));
        user.setLastname(request.get("lastName"));
        user.setEmail(request.get("email"));
        user.setPassword(request.get("password"));
        Role role = new Role();
        role.setRole(request.get("role"));
        user.setRoles(new HashSet<>(Arrays.asList(role)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String userName) {
            userRepository.delete(getUsersByUsername(userName));
            LOGGER.info("User successfully deleted");
    }
}