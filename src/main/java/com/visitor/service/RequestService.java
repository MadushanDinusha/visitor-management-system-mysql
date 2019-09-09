package com.visitor.service;

import com.visitor.domain.Request;

import java.util.List;

public interface RequestService {
    void saveRequest(Request request);

    void updateComment(String gorup_id, String comment);

    void updateRequestState(String groupId, String state);

    void updateReadState(String groupId, String AdminState, String empState);

    void updateEmpState(int user_id,String employeeState);

    List<Request> getRequestByGroupId(String groupId);
}
