package com.example.seebook.domain.wishlist.repository;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.wishlist.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long>, WishlistRepositoryCustom {

    void deleteByBookAndUser(Book book, User user);

    Optional<Wishlist> findByUserAndBook(User user, Book book);

    @Modifying
    @Query("DELETE FROM Wishlist w WHERE w.user = :user")
    void deleteAllByUser(@Param("user") User user);

}
