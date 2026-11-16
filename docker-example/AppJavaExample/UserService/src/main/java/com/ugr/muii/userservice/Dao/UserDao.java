package com.ugr.muii.userservice.Dao;

import com.ugr.muii.userservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    //boolean existsByUsername(String username);

}