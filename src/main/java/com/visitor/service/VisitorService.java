package com.visitor.service;

import com.visitor.domain.Visitor;

import java.util.List;

public interface VisitorService {
    void saveVisitor(Visitor visitor);

    Visitor findVisitorById(long id);

    List<Visitor> findVisitorByGroupId(String id);
}
