package com.visitor.repository;

import com.visitor.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    @Query("FROM Visitor WHERE nic = ?1")
    Visitor findVisitorByNic(String nic);

    List<Visitor> findVisitorByGroupId(String id);

    @Query("FROM Visitor WHERE responded_emp = ?1")
    List<Visitor> findVisitorByRespondedEmp(String userName);

    @Modifying
    @Query("UPDATE Visitor SET company = :company ,name = :name, nic = :nic,purpose = :purpose,date = :date WHERE id = :id")
    @Transactional
    void updateVisitorById(@Param("id") long id, @Param("company") String company,
                           @Param("name") String name, @Param("nic") String nic, @Param("purpose") String purpose, @Param("date") Timestamp date);


}
