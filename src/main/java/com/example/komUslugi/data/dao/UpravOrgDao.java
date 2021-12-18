package com.example.komUslugi.data.dao;

import com.example.komUslugi.data.domain.UprOrganizatsiya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpravOrgDao extends JpaRepository<UprOrganizatsiya, Long> {
}
