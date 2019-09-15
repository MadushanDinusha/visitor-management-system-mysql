package com.visitor.service;

import com.visitor.domain.Vehicle;
import com.visitor.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getVehicleListByGroupId(String groupId){
        return vehicleRepository.findVehicleByGroupId(groupId);
    }

    @Override
    public void updateVehicleNumber(int id, String vehicleNumber) {
        vehicleRepository.updateVehicleNumber(vehicleNumber,id);
    }
}
