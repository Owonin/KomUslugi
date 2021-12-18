package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.Zhiloe_pomeshenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZpDao extends JpaRepository<Zhiloe_pomeshenie, Long> {

    @Query(value = "SELECT * FROM Zhiloe_pomeshenie ", nativeQuery = true)
    List<Zhiloe_pomeshenie> findQAll();
}