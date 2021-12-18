package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.Zhilets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZhiletsDao extends JpaRepository<Zhilets, Long> {
}
