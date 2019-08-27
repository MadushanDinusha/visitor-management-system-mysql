package com.visitor.repository;

import com.visitor.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor,Long> {
    Visitor findVisitorById(long id);
    List<Visitor> findVisitorByGroupId(String id);

}
