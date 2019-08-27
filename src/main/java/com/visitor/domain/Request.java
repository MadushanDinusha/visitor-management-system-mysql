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

    @Column(name = "employee_id")
    private int employee_id;

    @Column(name = "state")
    private String state;

    public long getRequest_id() {
        return request_id;
    }

    public void setRequest_id(long request_id) {
        this.request_id = request_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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
                ", employee_id=" + employee_id +
                ", state='" + state + '\'' +
                '}';
    }
}