package com.visitor.repository;

import com.visitor.domain.Vehicle;
import com.visitor.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
     @Query("FROM Vehicle WHERE group_id = ?1")
     List<Vehicle> findVehicleByGroupId(String groupId);
}
