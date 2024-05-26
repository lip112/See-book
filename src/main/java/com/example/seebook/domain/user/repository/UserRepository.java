package com.example.seebook.domain.user.repository;

import com.example.seebook.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phone);

    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
}
