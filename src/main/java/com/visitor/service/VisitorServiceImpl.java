package com.visitor.service;

import com.visitor.domain.Visitor;
import com.visitor.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorRepository visitorRepository;

    @Override
    public void saveVisitor(Visitor visitor){
        visitorRepository.save(visitor);
    }
}
