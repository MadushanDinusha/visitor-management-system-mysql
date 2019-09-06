package com.visitor.repository;


import com.visitor.domain.User;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User getUsersByUsername(String username);

    void deleteUserByUsername(String userName);

    User getUserByEmail(String email);

    User getUserByUsername(String userName);

    @Modifying
    @Query("UPDATE User SET  password = :password WHERE username = :username")
    @Transactional
    void updatePassword(@Param("username") String username, @Param("password") String password);
}