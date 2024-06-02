package com.example.seebook.domain.book.controller;

import com.example.seebook.domain.book.service.BookService;
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
    @GetMapping("/text-search")
    public ResponseEntity<?> findBookByText(@RequestParam String Query, @RequestParam(defaultValue = "Keyword") String QueryType, @RequestParam(defaultValue = "1") int page) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.findBookByText(Query, QueryType, page));
    }
}
