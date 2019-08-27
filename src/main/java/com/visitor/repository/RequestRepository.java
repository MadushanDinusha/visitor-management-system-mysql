package com.visitor.repository;

import com.visitor.domain.Request;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("requestRepository")
public interface RequestRepository extends CrudRepository<Request,Long> {
    @Modifying
    @Query("UPDATE Request SET state = :state WHERE group_id = :group_id")
    @Transactional
    void updateVisitorState(@Param("group_id") String group_id, @Param("state") String state);
}
