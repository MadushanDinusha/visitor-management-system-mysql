package com.visitor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_details")
public class Vehicle {

    @Id
    private int id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", groupId='" + groupId + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';
    }
}
