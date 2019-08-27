package com.visitor.service;

import com.visitor.domain.Request;
import com.visitor.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService{

    @Qualifier("requestRepository")
    @Autowired
    RequestRepository requestRepository;

    public void saveRequest(Request request){
        requestRepository.save(request);
    }
}
