package com.visitor.repository;

import com.visitor.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    @Query("FROM Visitor WHERE nic = ?1")
    Visitor findVisitorByNic(String nic);

    List<Visitor> findVisitorByGroupId(String id);

    @Query("FROM Visitor WHERE responded_emp = ?1")
    List<Visitor> findVisitorByRespondedEmp(String userName);

    @Modifying
    @Query("UPDATE Visitor SET company = :company ,name = :name, nic = :nic,purpose = :purpose,date = :date, time= :time WHERE id = :id")
    @Transactional
    void updateVisitorById(@Param("id") long id, @Param("company") String company,
                           @Param("name") String name, @Param("nic") String nic, @Param("purpose") String purpose,
                           @Param("date") Date date,@Param("time") String time);


    @Modifying
    @Query("UPDATE Visitor SET check_in = :check_in WHERE id = :id")
    @Transactional
    void updateVisitorCheckIn(@Param("check_in") String check_in, @Param("id") long id);

    @Modifying
    @Query("UPDATE Visitor SET check_out = :check_out WHERE id = :id")
    @Transactional
    void updateVisitorCheckOut(@Param("check_out") String check_out, @Param("id") long id);

    @Modifying
    @Query("UPDATE Visitor SET pass_id = :pass_id WHERE id = :id")
    @Transactional
    void updateVisitorPassId(@Param("pass_id") String pass_id,@Param("id") long id);

    @Query("FROM Visitor WHERE date between ?1 and ?2 ")
    List<Visitor> getVisitorDetailsByDate(Date fromDate, Date toDate);
}
