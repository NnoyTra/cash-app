package com.nnoytra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnoytra.entities.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
