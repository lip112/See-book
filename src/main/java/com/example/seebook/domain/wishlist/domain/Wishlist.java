package com.example.seebook.domain.wishlist.domain;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;
}
