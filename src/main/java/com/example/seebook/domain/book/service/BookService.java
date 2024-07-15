package com.example.seebook.domain.book.service;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.dto.response.BookListResponseDTO;
import com.example.seebook.domain.book.repository.BookRepository;
import com.example.seebook.domain.book.dto.response.BookInReviewListResponseDTO;
import com.example.seebook.domain.main.dto.response.CategoryResponseDTO;
import com.example.seebook.domain.main.dto.response.JoinMainPageResponseDTO;
import com.example.seebook.domain.main.dto.response.NewBookResponseDTO;
import com.example.seebook.domain.review.repository.ReviewRepository;
import com.example.seebook.global.exception.BookException;
import com.example.seebook.global.exception.ReviewException;
import com.example.seebook.global.restclient.AladinComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AladinComponent aladinComponent;
    private final ReviewRepository reviewRepository;

    @Value("${aladin.ttbKey}")
    private String TTB_KEY;

    public BookListResponseDTO getBookByText(String query, String queryType, int start) {
        BookListResponseDTO alainBookList = BookListResponseDTO.from(aladinComponent.findAllByQuery(TTB_KEY, query, queryType, "js", start, 20131101));
        return bookRepository.getBooksReviewSummary(alainBookList);
    }

    public BookInReviewListResponseDTO findByAladin(String isbn13) {
        Map<String, Object> byIsbn13 = aladinComponent.findByIsbn13(TTB_KEY, isbn13, "ISBN", "js", 20131101);
        if (byIsbn13.get("item") == null) {
            throw new BookException.NotFoundBookException();
        }
        return BookInReviewListResponseDTO.fromMap(aladinComponent.findByIsbn13(TTB_KEY, isbn13, "ISBN", "js", 20131101));
    }

    public BookDTO getDetailBook(String isbn13) {
        BookDTO bookDTO = BookDTO.form(bookRepository.findByIsbn13(isbn13)
                .orElseThrow(BookException.NotFoundBookException::new));
        return bookRepository.getBooksReviewSummary(bookDTO);
    }

    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(BookException.NotFoundBookException::new);
    }

    public boolean validationDBInIsbn13(String isbn13) {
        Optional<Book> byIsbn13 = bookRepository.findByIsbn13(isbn13);
        return byIsbn13.isPresent();
    }

    public Book saveBook(BookDTO bookDTO) {
        return bookRepository.save(Book.form(bookDTO));
    }
    public BookInReviewListResponseDTO saveAladinBook(BookInReviewListResponseDTO bookInReviewListResponseDTO) {
        Long bookId = bookRepository.save(Book.form(bookInReviewListResponseDTO.getBook())).getBookId();
        bookInReviewListResponseDTO.getBook().changeBookId(bookId);
        return bookInReviewListResponseDTO;
    }

    public List<BookDTO> getMainNewBooks() {
        try{
            List<BookDTO> list = reviewRepository.findTop10ByOrderByReviewIdDesc()
                    .stream()
                    .map(review ->
                            BookDTO.builder()
                                    .title(review.getBook().getTitle())
                                    .imageLink(review.getBook().getImageLink())
                                    .isbn13(review.getBook().getIsbn13())
                                    .build())
                    .limit(3)
                    .toList();
            return list;
        } catch (Exception e) {
            throw new ReviewException.NotFoundReviewException();
        }
    }

    public NewBookResponseDTO getNewBookList(int page) {
        return bookRepository.getNewBooks((page-1)*10, 10);
    }
    public List<JoinMainPageResponseDTO.BookWithReview> getBestBooks() {
        try{
            return bookRepository.getBestBooks();
        } catch (Exception e) {
            throw new ReviewException.NotFoundReviewException();
        }
    }

    public CategoryResponseDTO findCategoryBooks(String categoryName, int page) {
        return bookRepository.findCategoryBooks(categoryName, (page-1)*10, 10);
    }
}
