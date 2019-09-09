package com.visitor.service;

import com.visitor.domain.Visitor;

import java.util.List;

public interface VisitorService {
    void saveVisitor(Visitor visitor);

    Visitor findVisitorById(String id);

    List<Visitor> findVisitorByGroupId(String id);

    void updateVisitor(Visitor visitor);

    List<Visitor> getAllVisitors();
}
