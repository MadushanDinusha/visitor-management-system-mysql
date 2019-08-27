package com.visitor.repository;

import com.visitor.domain.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("requestRepository")
public interface RequestRepository extends CrudRepository<Request,Long> {
}
