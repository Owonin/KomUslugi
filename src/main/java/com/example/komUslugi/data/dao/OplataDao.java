package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.Komm_uslugi;
import com.example.komUslugi.data.domain.Oplata;
import com.example.komUslugi.data.domain.Zhiloe_pomeshenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OplataDao extends JpaRepository<Oplata, Long> {

    @Query(value = "SELECT * FROM oplata " +
            "WHERE oplata.Sostoyanie_oplati = false and oplata.zhilets_kod_zhiltsa = :param", nativeQuery = true)
   Optional<List<Oplata>> findByUndone(@Param("param") Long id);

    @Query(value = "SELECT * FROM oplata "+
            "WHERE oplata.usluga_kod_uslugi = :param and oplata.zhilets_kod_zhiltsa = :param3 and oplata.zhiloe_pomeshenie_kod_zhilogo_pomesheniya = :param2 and oplata.sostoyanie_oplati = false", nativeQuery = true)
    Optional<List<Oplata>> findByUndoneByParam(@Param("param") Long usluga,
                                              @Param("param2") Long zp,
                                 @Param("param3") Long id);

    @Query(value = "SELECT * FROM oplata " +
            "WHERE oplata.zhilets_kod_zhiltsa = :param and oplata.Sostoyanie_oplati = true and oplata.zhilets_kod_zhiltsa = :param", nativeQuery = true)
    Optional<List<Oplata>> findByDone(@Param("param") Long id);
}
