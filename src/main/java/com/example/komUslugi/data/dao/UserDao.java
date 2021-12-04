package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {


    @Query(value = "SELECT * FROM test_user", nativeQuery = true)
    List<User> findQAll();

}
