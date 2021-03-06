package com.visitor.repository;

import com.visitor.domain.Vehicle;
import com.visitor.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
     @Query("FROM Vehicle WHERE group_id = ?1")
     List<Vehicle> findVehicleByGroupId(String groupId);

     @Modifying
     @Query("UPDATE Vehicle SET vehicleNumber = :vehicleNumber WHERE id = :id")
     @Transactional
     void updateVehicleNumber(@Param("vehicleNumber") String check_in, @Param("id") int id);

}
