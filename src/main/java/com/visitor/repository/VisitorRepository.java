package com.visitor.repository;

import com.visitor.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor,Long> {
}
