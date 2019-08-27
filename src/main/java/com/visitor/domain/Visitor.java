package com.visitor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="visitor_details")
public class Visitor {


    @Id
    @Column(name="id")
    private long id;

    @Column(name="group_id")
    private String groupId;

    @Column(name = "name")
    private String name;

    @Column(name="purpose")
    private String purpose;

    @Column(name="company")
    private String company;

    @Column(name="vehicle_number")
    private String vehicleNumber;

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Timestamp date;

    @Column(name = "responded_emp")
    private String userName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", purpose='" + purpose + '\'' +
                ", company='" + company + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", date=" + date +
                ", userName='" + userName + '\'' +
                '}';
    }
}