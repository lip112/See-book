package com.example.seebook.domain.profile.repository;

import com.example.seebook.domain.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom {
}
