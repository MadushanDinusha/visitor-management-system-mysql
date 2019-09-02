package com.visitor.service;

import com.visitor.controller.ApplicationController;
import com.visitor.domain.Request;
import com.visitor.domain.User;
import com.visitor.domain.Visitor;
import com.visitor.repository.RequestRepository;
import com.visitor.repository.VisitorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationController.class);
    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    UserService userService;

    @Autowired
    RequestService requestService;

    @Qualifier("requestRepository")
    @Autowired
    RequestRepository requestRepository;

    @Override
    public void saveVisitor(Visitor visitor) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        visitor.setUserName(authentication.getName());
        LOGGER.info("visitor {}", visitor);
        User employee = userService.getUsersByUsername(authentication.getName());
        Request request = new Request();
        request.setRequest_id(visitor.getNic());
        request.setUser_id(employee.getId());
        request.setGroup_id(visitor.getGroupId());
        request.setState("Pending");
        LOGGER.info("request details {}", request);
        requestService.saveRequest(request);
        visitorRepository.save(visitor);
    }

    public Visitor findVisitorById(String id) {
        return visitorRepository.findVisitorByNic(id);
    }

    public List<Visitor> findVisitorByGroupId(String id) {
        return visitorRepository.findVisitorByGroupId(id);
    }

    public void updateVisitor(String groupId,String state) {
        LOGGER.info("updating state group_id {}, state {}",groupId,state);
        requestRepository.updateVisitorState(groupId,state);
    }
}
