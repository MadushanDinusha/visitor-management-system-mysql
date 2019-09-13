package com.visitor.service;

import com.visitor.domain.Visitor;

import java.sql.Timestamp;
import java.util.List;

public interface VisitorService {
    void saveVisitor(Visitor visitor);

    Visitor findVisitorById(String id);

    List<Visitor> findVisitorByGroupId(String id);

    void updateVisitor(Visitor visitor);

    List<Visitor> getAllVisitors();

    void updateVisitorCheckIn(String checkIn, long id);

    void updateVisitorCheckOut(String checkIn, long id);

    void updateVisitorPassId(String passId, long VisitorId);

    List<Visitor> getVisitorDetailsByDate(java.sql.Date fromDate, java.sql.Date toDate);
}
