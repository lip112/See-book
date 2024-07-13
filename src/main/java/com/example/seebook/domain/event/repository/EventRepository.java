package com.example.seebook.domain.event.repository;

import com.example.seebook.domain.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom{
    List<Event> findByEndDateAfter(LocalDateTime now);
}
