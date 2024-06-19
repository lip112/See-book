package com.example.seebook.domain.book.controller;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.service.BookService;
import com.example.seebook.domain.book.dto.response.BookInReviewListResponseDTO;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.domain.user.service.UserService;
import com.example.seebook.domain.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;
    private final ReviewService reviewService;
    private final WishlistService wishlistService;
    private final UserService userService;
    @GetMapping("/text-search")
    public ResponseEntity<?> findBookByText(@RequestParam String query, @RequestParam(defaultValue = "Keyword") String queryType, @RequestParam(defaultValue = "1") int page) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getBookByText(query, queryType, page));
    }

    @GetMapping("/detail-search")
    public ResponseEntity<BookInReviewListResponseDTO> findDetailSearch(@RequestParam String isbn13,
                                                                        @RequestParam(defaultValue = "1") int page, @RequestParam("userId") Long userId) {
        if (bookService.validationDBInIsbn13(isbn13)){
            BookDTO detailBook = bookService.getDetailBook(isbn13);
            BookInReviewListResponseDTO detailBookWithReviewList = reviewService.getBookInReviewList(detailBook, page);
            Book book = bookService.findById(detailBook.getBookId());
            User user = userService.findById(userId);
            detailBookWithReviewList.changeWishlistStatus(wishlistService.getWishedStatus(user, book));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(detailBookWithReviewList);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bookService.findByAladin(isbn13));
        }
    }
}
