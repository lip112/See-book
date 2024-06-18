package com.example.seebook.domain.wishlist.repository;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.wishlist.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>, WishlistRepositoryCustom {

    void deleteByBookAndUserAndWishlistId(Book book, User user, Long wishlistId);

    Optional<Wishlist> findByUserAndBook(User user, Book book);
}
