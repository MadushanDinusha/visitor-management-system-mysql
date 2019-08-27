package com.visitor.repository;

import com.visitor.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor,Long> {
    Visitor findVisitorById(long id);
    List<Visitor> findVisitorByGroupId(String id);
}
