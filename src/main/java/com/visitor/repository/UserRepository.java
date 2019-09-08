package com.visitor.repository;


import com.visitor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



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

    @Modifying
    @Query("UPDATE User SET  email = :email, hodMail =:hodMail, department = :department WHERE username = :username")
    @Transactional
    void updateUser(@Param("username") String username, @Param("email") String email, @Param("hodMail") String hodMail,
                    @Param("department") String department);
}