package com.example.komUslugi.data.dao;


import com.example.komUslugi.data.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
}
