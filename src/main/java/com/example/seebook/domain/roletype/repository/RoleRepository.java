package com.example.seebook.domain.roletype.repository;

import com.example.seebook.domain.roletype.domain.RoleCode;
import com.example.seebook.domain.roletype.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleType, RoleCode> {
}
