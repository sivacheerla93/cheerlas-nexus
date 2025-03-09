package com.cheerlasgroup.nexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheerlasgroup.nexus.entity.UserDetailsEntity;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {
    UserDetailsEntity findByUsername(String username);
}
