package com.visitor.repository;

import com.visitor.domain.Request;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository("requestRepository")
public interface RequestRepository extends CrudRepository<Request,Long> {
    @Modifying
    @Query("UPDATE Request SET lastUpdatedTime = :lastUpdatedTime, state = :state WHERE group_id = :group_id")
    @Transactional
    void updateRequestState(@Param("group_id") String group_id, @Param("lastUpdatedTime") Timestamp lastUpdatedTime, @Param("state") String state);

    @Query("FROM Request WHERE user_id = ?1")
    List<Request> findRequestsByUser_id(int user_id);

    @Modifying
    @Query("UPDATE Request SET comment = :comment WHERE group_id = :group_id")
    @Transactional
    void updateComment(@Param("group_id") String group_id, @Param("comment") String comment);

    @Modifying
    @Query("UPDATE Request SET adminState = :adminState, employeeState = :employeeState WHERE group_id = :group_id")
    @Transactional
    void updateReadState(@Param("group_id") String group_id, @Param("adminState") String adminState, @Param("employeeState") String employeeState);

    @Modifying
    @Query("UPDATE Request SET  employeeState = :employeeState WHERE user_id = :user_id")
    @Transactional
    void updateEmpState(@Param("user_id") int user_id, @Param("employeeState") String employeeState);

    @Query("FROM Request WHERE group_id = ?1")
    List<Request> getRequestByGroup_id(String groupId);
}
