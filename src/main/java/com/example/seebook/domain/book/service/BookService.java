package com.example.seebook.domain.book.service;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.dto.response.BookListResponseDTO;
import com.example.seebook.domain.book.repository.BookRepository;
import com.example.seebook.domain.review.dto.response.BookInReviewListResponseDTO;
import com.example.seebook.domain.review.service.ReviewService;
import com.example.seebook.global.exception.BookException;
import com.example.seebook.global.restclient.AladinComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AladinComponent aladinComponent;

    private final String TTB_KEY ="ttbwhaud15971218001";

    public BookListResponseDTO getBookByText(String Query, String QueryType, int start) {
        BookListResponseDTO alainBookList = BookListResponseDTO.form(aladinComponent.findAllByQuery(TTB_KEY, Query, QueryType, "js", start, 20131101));
        return bookRepository.getBooksReviewSummary(alainBookList);
    }

    public BookInReviewListResponseDTO findByAladin(String isbn13) {
        return BookInReviewListResponseDTO.fromMap(aladinComponent.findByIsbn13(TTB_KEY, isbn13, "ISBN", "js", 20131101));
    }

    public BookDTO getDetailBook(String isbn13) { //DTO로 변환해서 반환
        BookDTO bookDTO = BookDTO.form(bookRepository.findByIsbn13(isbn13)
                .orElseThrow(BookException.NotFoundBookException::new));
        return bookRepository.getBooksReviewSummary(bookDTO);
    }

    public Book getById(Long bookId) { //DTO로 변환해서 반환
        return bookRepository.findById(bookId)
                .orElseThrow(BookException.NotFoundBookException::new);
    }

    public boolean validationDBInIsbn13(String isbn13) {
        Optional<Book> byIsbn13 = bookRepository.findByIsbn13(isbn13);
        if (byIsbn13.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
