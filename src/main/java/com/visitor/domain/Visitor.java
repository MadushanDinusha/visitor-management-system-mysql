package com.visitor.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="visitor_details")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="nic")
    private String NIC;

    @Column(name = "name")
    private String name;

    @Column(name="purpose")
    private String purpose;

    @Column(name="company")
    private String company;

    @Column(name="vehicle_number")
    private String vehicleNumber;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "responded_emp")
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", NIC='" + NIC + '\'' +
                ", name='" + name + '\'' +
                ", purpose='" + purpose + '\'' +
                ", company='" + company + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", date=" + date +
                ", userName='" + userName + '\'' +
                '}';
    }
}
