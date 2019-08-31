package com.visitor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class Request {

    @Id
    private long request_id;

    @Column(name="group_id")
    private String group_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "state")
    private String state;

    public long getRequest_id() {
        return request_id;
    }

    public void setRequest_id(long request_id) {
        this.request_id = request_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request_id='" + request_id + '\'' +
                ", employee_id=" + user_id +
                ", state='" + state + '\'' +
                '}';
    }
}