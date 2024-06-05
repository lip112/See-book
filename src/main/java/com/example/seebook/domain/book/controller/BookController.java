package com.example.seebook.domain.book.controller;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.service.BookService;
import com.example.seebook.domain.review.dto.response.BookInReviewListResponseDTO;
import com.example.seebook.domain.review.service.ReviewService;
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
    @GetMapping("/text-search")
    public ResponseEntity<?> findBookByText(@RequestParam String query, @RequestParam(defaultValue = "Keyword") String queryType, @RequestParam(defaultValue = "1") int page) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getBookByText(query, queryType, page));
    }

    @GetMapping("/detail-search")
    public ResponseEntity<?> findDetailSearch(@RequestParam String isbn13, @RequestParam(defaultValue = "1") int page) {
        if (bookService.validationDBInIsbn13(isbn13)){
            Book book = bookService.getDetailBook(isbn13);
            BookInReviewListResponseDTO reviewList = reviewService.getReviewList(book, page);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(reviewList);
        } else {
            bookService.findByAladin(isbn13);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }
}
