package com.example.seebook.domain.suspend.repository;

import com.example.seebook.domain.suspend.domain.Suspend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspendRepository extends JpaRepository<Suspend, Long> {
}
