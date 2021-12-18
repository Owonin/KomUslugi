package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    Optional<User> findByLogin(String login);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findQAll();

}
