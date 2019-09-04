package com.visitor.service;

import com.visitor.domain.Request;
import com.visitor.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RequestServiceImpl implements RequestService{

    @Qualifier("requestRepository")
    @Autowired
    RequestRepository requestRepository;

    public void saveRequest(Request request){
        requestRepository.save(request);
    }

    @Override
    public void updateComment(String group_id, String comment) {
        requestRepository.updateComment(group_id,comment);
    }

    public void updateRequestState(String groupId, String state) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        requestRepository.updateRequestState(groupId,timestamp,state);
    }

    @Override
    public void updateReadState(String groupId, String AdminState, String empState){
        requestRepository.updateReadState(groupId,AdminState,empState);
    }
}
