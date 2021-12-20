package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.Querys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryDao extends JpaRepository<Querys, Long> {
}
