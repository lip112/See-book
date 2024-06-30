package com.example.seebook.domain.wishlist.controller;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.service.BookService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.domain.wishlist.dto.request.AddWishlistRequestDTO;
import com.example.seebook.domain.wishlist.dto.request.DeleteWishlistRequestDTO;
import com.example.seebook.domain.wishlist.dto.request.GetWishlistRequestDTO;
import com.example.seebook.domain.wishlist.dto.response.GetWishlistResponseDTO;
import com.example.seebook.domain.wishlist.service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    private final BookService bookService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addWishlist(@Valid @RequestBody AddWishlistRequestDTO addWishlistRequestDTO) {
        if (bookService.validationDBInIsbn13(addWishlistRequestDTO.getBook().getIsbn13())) {
            Book book = bookService.findById(addWishlistRequestDTO.getBook().getBookId());
            User user = userService.findById(addWishlistRequestDTO.getUserId());
            wishlistService.addWishlist(book, user);
        } else {
            Book book = bookService.saveBook(addWishlistRequestDTO.getBook());
            User user = userService.findById(addWishlistRequestDTO.getUserId());
            wishlistService.addWishlist(book, user);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteWishlist(@Valid @RequestBody DeleteWishlistRequestDTO deleteWishlistRequestDTO) {
        Book book = bookService.findById(deleteWishlistRequestDTO.getBookId());
        User user = userService.findById(deleteWishlistRequestDTO.getUserId());
        wishlistService.deleteWishlist(book, user, deleteWishlistRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/list")
    public ResponseEntity<GetWishlistResponseDTO> getWishlist(@Valid @RequestBody GetWishlistRequestDTO getWishlistRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wishlistService.getWishlist(getWishlistRequestDTO));

    }
}
