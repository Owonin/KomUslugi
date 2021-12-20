package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.Zhilets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZhiletsDao extends JpaRepository<Zhilets, Long> {

    @Query(value = "SELECT * FROM Zhilets " +
            "WHERE Zhilets.Kod_zhiltsa = :param", nativeQuery = true)
    Zhilets findByKod(@Param("param") Long Kod_zhiltsa);
}
