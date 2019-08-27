package com.visitor.service;

import com.visitor.controller.ApplicationController;
import com.visitor.domain.Request;
import com.visitor.domain.User;
import com.visitor.domain.Visitor;
import com.visitor.repository.VisitorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
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

    @Override
    public void saveVisitor(Visitor visitor) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        visitor.setUserName(authentication.getName());
        LOGGER.info("visitor {}", visitor);
        User employee = userService.getUsersByUsername(authentication.getName());
        Request request = new Request();
        request.setRequest_id(visitor.getId());
        request.setEmployee_id(employee.getId());
        request.setGroup_id(visitor.getGroupId());
        request.setState("Pending");
        LOGGER.info("request details {}", request);
        requestService.saveRequest(request);
        visitorRepository.save(visitor);
    }

    public Visitor findVisitorById(long id){
        return visitorRepository.findVisitorById(id);
    }

    public List<Visitor> findVisitorByGroupId(String id){
        return visitorRepository.findVisitorByGroupId(id);
    }
}
