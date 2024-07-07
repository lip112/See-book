package com.example.seebook.domain.wishlist.service;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.wishlist.domain.Wishlist;
import com.example.seebook.domain.wishlist.dto.request.DeleteWishlistRequestDTO;
import com.example.seebook.domain.wishlist.dto.response.GetWishlistResponseDTO;
import com.example.seebook.domain.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    public void addWishlist(Book book, User user) {
        wishlistRepository.findByUserAndBook(user, book).ifPresent(wishlist -> {
            throw new IllegalArgumentException("이미 찜한 책입니다.");
        });
        wishlistRepository.save(Wishlist.builder()
                .book(book)
                .user(user)
                .build()
        );
    }

    @Transactional
    public void deleteWishlist(Book book, User user, DeleteWishlistRequestDTO deleteWishlistRequestDTO) {
        wishlistRepository.deleteByBookAndUserAndWishlistId(book, user, deleteWishlistRequestDTO.getWishlistId());//만들긴 했는데 삭제 유무?
    }

    public GetWishlistResponseDTO getWishlist(int page, Long userId) {
        return wishlistRepository.getWIshlist(userId,
                (page -1) *10 , page*10-1);
    }

    public boolean getWishedStatus(User user, Book book) {
        Optional<Wishlist> wished = wishlistRepository.findByUserAndBook(user, book);
        return wished.isPresent();
    }
}
