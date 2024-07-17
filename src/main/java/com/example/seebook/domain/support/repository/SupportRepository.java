package com.example.seebook.domain.support.repository;

import com.example.seebook.domain.support.domain.Support;
import com.example.seebook.domain.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupportRepository extends JpaRepository<Support, Long>, SupportRepositoryCustom{
    Optional<Support> findByUserId(User user);

    void deleteByUserId(User user);
}
