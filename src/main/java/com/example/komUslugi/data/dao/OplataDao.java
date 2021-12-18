package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.Oplata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OplataDao extends JpaRepository<Oplata, Long> {
}
