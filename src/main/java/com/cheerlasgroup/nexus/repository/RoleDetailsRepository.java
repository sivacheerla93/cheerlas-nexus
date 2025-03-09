package com.cheerlasgroup.nexus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheerlasgroup.nexus.entity.RoleDetailsEntity;

@Repository
public interface RoleDetailsRepository extends JpaRepository<RoleDetailsEntity, Long> {

    Optional<RoleDetailsEntity> findByName(String role);

}
