package com.visitor.service;

import com.visitor.domain.Vehicle;
import com.visitor.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
