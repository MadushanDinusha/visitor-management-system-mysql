package com.visitor.service;

import com.visitor.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUsersByUsername(String username);

    void saveUser(Map<String,String> user);

    List<User> getAllUsers();

    void deleteUser(String userName);
}