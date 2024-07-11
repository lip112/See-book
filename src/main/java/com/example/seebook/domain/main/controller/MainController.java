package com.example.seebook.domain.main.controller;

import com.example.seebook.domain.book.service.BookService;
import com.example.seebook.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {
    private final ReviewService reviewService;
    private final BookService bookService;

}
