package com.visitor.service;

import com.visitor.domain.Vehicle;

import java.util.List;

public interface VehicleService {
    void save(Vehicle vehicle);

    List<Vehicle> getVehicleListByGroupId(String groupId);
}
