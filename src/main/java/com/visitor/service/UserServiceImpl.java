package com.visitor.service;

import com.visitor.domain.Request;
import com.visitor.domain.Role;
import com.visitor.domain.User;
import com.visitor.repository.RequestRepository;
import com.visitor.repository.RoleRepository;
import com.visitor.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("requestRepository")
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User getUsersByUsername(String username) {
        return userRepository.getUsersByUsername(username);
    }

    @Override
    public void saveUser(Map<String, String> request) {
        User user = new User();
        user.setUsername(request.get("userName"));
        user.setEmail(request.get("email"));
        user.setPassword(request.get("password"));
        user.setHodMail(request.get("hodEmail"));
        user.setDepartment(request.get("department"));
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

    @Override
    public User getAdmin(String hodEmail) {
        return userRepository.getUserByEmail(hodEmail);
    }

    @Override
    public List<Request> getRequest() {
        Comparator<Request> compareByTime = (Request r1, Request r2) -> (r1.getLastUpdatedTime()).compareTo((r2.getLastUpdatedTime()));
        List<Request> requestList = (List<Request>) requestRepository.findAll();
        Collections.sort(requestList, compareByTime.reversed());
        return requestList;
    }

    @Override
    public List<Request> getRequestByUserName(int employee_id) {
        Comparator<Request> compareByTime = (Request r1, Request r2) -> (r1.getLastUpdatedTime()).compareTo((r2.getLastUpdatedTime()));
        List<Request> requestList = requestRepository.findRequestsByUser_id(employee_id);
        Collections.sort(requestList, compareByTime);
        return requestList;
    }

    @Override
    public int getIdByUserName(String userName) {
        return userRepository.getUserByUsername(userName).getId();
    }

    @Override
    public void updatePassword(String userName,String password){
            userRepository.updatePassword(userName,bCryptPasswordEncoder.encode(password));
    }
}