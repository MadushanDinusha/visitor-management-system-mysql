package com.visitor.repository;

import com.visitor.domain.Request;
import com.visitor.domain.User;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import java.util.List;

@Repository("requestRepository")
public interface RequestRepository extends CrudRepository<Request,Long> {
    @Modifying
    @Query("UPDATE Request SET state = :state WHERE group_id = :group_id")
    @Transactional
    void updateVisitorState(@Param("group_id") String group_id, @Param("state") String state);

    @Query("FROM Request WHERE user_id = ?1")
    List<Request> findRequestsByUser_id(int user_id);
}
