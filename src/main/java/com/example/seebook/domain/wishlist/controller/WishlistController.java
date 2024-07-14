package com.example.seebook.domain.wishlist.controller;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.service.BookService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.domain.wishlist.dto.request.AddWishlistRequestDTO;
import com.example.seebook.domain.wishlist.dto.request.DeleteWishlistRequestDTO;
import com.example.seebook.domain.wishlist.dto.response.GetWishlistResponseDTO;
import com.example.seebook.domain.wishlist.service.WishlistService;
import com.example.seebook.global.jwt.UserAuthorizationUtil;
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
        Long userId = UserAuthorizationUtil.getLoginUserId();
        Book book = bookService.findById(addWishlistRequestDTO.getBookId());
        User user = userService.findById(userId);

        wishlistService.addWishlist(book, user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteWishlist(@Valid @RequestBody DeleteWishlistRequestDTO deleteWishlistRequestDTO) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        Book book = bookService.findById(deleteWishlistRequestDTO.getBookId());
        User user = userService.findById(userId);
        wishlistService.deleteWishlist(book, user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/list")
    public ResponseEntity<GetWishlistResponseDTO> getWishlist(@RequestParam("page") int page) {
        Long userId = UserAuthorizationUtil.getLoginUserId();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wishlistService.getWishlist(page, userId));
    }
}
