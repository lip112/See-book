package com.example.seebook.domain.book.service;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.dto.response.BookListResponseDTO;
import com.example.seebook.domain.book.repository.BookRepository;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.global.exception.BookException;
import com.example.seebook.global.restclient.AladinComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AladinComponent aladinComponent;
    private final ReviewService reviewService;

    private final String TTB_KEY ="ttbwhaud15971218001";

    public BookListResponseDTO getBookByText(String Query, String QueryType, int start) {
        return BookListResponseDTO.form(aladinComponent.findAllByQuery(TTB_KEY, Query, QueryType, "js", start, 20131101));
    }

    public Book getDetailBook(String isbn13) {
        return bookRepository.findByIsbn13(isbn13)
                .orElseThrow(BookException.NotFoundBookException::new);
    }
    public void findByAladin(String isbn13) {
        Map<String, Object> bookInfo = aladinComponent.findByIsbn13(TTB_KEY, isbn13, "ISBN", "js", 20131101);
    }

    public Book getById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(BookException.NotFoundBookException::new);
    }

    public boolean validationDBInIsbn13(String isbn13) {
        bookRepository.findByIsbn13(isbn13)
                .orElseThrow(BookException.NotFoundBookException::new);
        return true;
    }
}
