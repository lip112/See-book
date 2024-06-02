package com.example.seebook.domain.review.controller;

import com.example.seebook.domain.review.dto.request.WriteReviewRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    @PostMapping("/write")
    public ResponseEntity<?> register(@RequestBody WriteReviewRequestDTO writeRequestDTO) {
        System.out.println(writeRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(writeRequestDTO);
    }
}
