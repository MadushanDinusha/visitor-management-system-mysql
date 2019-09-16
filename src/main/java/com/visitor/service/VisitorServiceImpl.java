package com.visitor.service;

import com.visitor.domain.Request;
import com.visitor.domain.User;
import com.visitor.domain.Visitor;
import com.visitor.repository.RequestRepository;
import com.visitor.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        visitor.setUserName(authentication.getName());
        User employee = userService.getUsersByUsername(authentication.getName());
        Request request = new Request();
        request.setRequest_id(visitor.getNic());
        request.setUser_id(employee.getId());
        request.setGroup_id(visitor.getGroupId());
        request.setLastUpdatedTime(timestamp);
        request.setState("Pending");
        request.setAdminState("UnRead");
        request.setEmployeeState("Read");
        requestService.saveRequest(request);
        visitorRepository.save(visitor);
    }

    public Visitor findVisitorById(String id) {
        return visitorRepository.findVisitorByNic(id);
    }

    public List<Visitor> findVisitorByGroupId(String id) {
        return visitorRepository.findVisitorByGroupId(id);
    }

    public void updateVisitor(Visitor visitor){
        visitorRepository.updateVisitorById( visitor.getId(),visitor.getCompany(),visitor.getName(),visitor.getNic(),visitor.getPurpose(),visitor.getDate(),visitor.getTime());
    }

    public List<Visitor> getAllVisitors(){
        return visitorRepository.findAll();
    }

    public void updateVisitorCheckIn(String checkIn, long id){
        visitorRepository.updateVisitorCheckIn(checkIn,id);
    }

    public void updateVisitorCheckOut(String checkOut, long id){
        visitorRepository.updateVisitorCheckOut(checkOut,id);
    }

    public void updateVisitorPassId(String passId, long visitorId){
        visitorRepository.updateVisitorPassId(passId,visitorId);
    }

    public List<Visitor> getVisitorDetailsByDate(java.sql.Date fromDate, java.sql.Date toDate){
        List<Visitor> returnVisitorList = new ArrayList<>();
        List<Visitor> visitorList = visitorRepository.getVisitorDetailsByDate(fromDate,toDate);
        for(Visitor visitor : visitorList){
            if(visitor.getCheckIn() !=null && visitor.getCheckOut() !=null){
                returnVisitorList.add(visitor);
            }
        }
       return returnVisitorList;
    }
}
