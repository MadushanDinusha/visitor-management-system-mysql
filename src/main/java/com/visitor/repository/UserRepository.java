package com.visitor.repository;


import com.visitor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User getUsersByUsername(String username);
    void deleteUserByUsername(String userName);
}