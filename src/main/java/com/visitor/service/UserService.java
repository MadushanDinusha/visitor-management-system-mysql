package com.visitor.service;

import com.visitor.domain.User;

public interface UserService {

    public User getUsersByUsername(String username);

    public void saveUser(User user);
}