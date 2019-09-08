package com.visitor.repository;

import com.visitor.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
    Role findRoleById(int id);
    @Modifying
    @Query("UPDATE Role SET  role = :role WHERE role_id = :role_id")
    @Transactional
    void updateRoleByUserId(@Param("role") String role, @Param("role_id") int role_id);
}