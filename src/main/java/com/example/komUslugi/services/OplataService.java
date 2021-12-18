package com.example.komUslugi.services;

import com.example.komUslugi.data.domain.Oplata;
import com.example.komUslugi.data.domain.User;

import java.util.List;

public interface OplataService {
    User findById(Long id) ;
    List<Oplata> findAll();
    void save(Oplata oplata);
    void setData(String date);
}

