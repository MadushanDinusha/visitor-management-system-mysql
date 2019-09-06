package com.visitor.service;

import com.visitor.domain.Request;
import com.visitor.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    User getUsersByUsername(String username);

    void saveUser(Map<String, String> user);

    List<User> getAllUsers();

    void deleteUser(String userName);

    User getAdmin(String hodEmail);

    List<Request> getRequest();

    List<Request> getRequestByUserName(int employee_id);

    int getIdByUserName(String userName);

    void updatePassword(String userName, String password);
}