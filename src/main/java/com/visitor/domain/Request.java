package com.visitor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "request_id")
    private String request_id;

    @Column(name = "group_id")
    private String group_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "state")
    private String state;

    @Column(name = "comment")
    private String comment;

    @Column(name="lastUpdatedTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp lastUpdatedTime;

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
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
                "id=" + id +
                ", request_id='" + request_id + '\'' +
                ", group_id='" + group_id + '\'' +
                ", user_id=" + user_id +
                ", state='" + state + '\'' +
                ", comment='" + comment + '\'' +
                ", lastUpdatedTime='" + lastUpdatedTime + '\'' +
                '}';
    }
}