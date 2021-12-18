package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.Komm_uslugi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicesDao extends JpaRepository<Komm_uslugi, Long> {
    @Query(value = "SELECT * FROM Kommunalnye_uslugi ", nativeQuery = true)
    List<Komm_uslugi> findQAll();
    Optional<Komm_uslugi> findByKod_usligi();
}
