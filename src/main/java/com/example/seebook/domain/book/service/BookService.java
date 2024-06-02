package com.example.seebook.domain.book.service;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.dto.response.BookListResponseDTO;
import com.example.seebook.domain.book.repository.BookRepository;
import com.example.seebook.domain.user.domain.User;
import com.example.seebook.global.exception.BookException;
import com.example.seebook.global.exception.UserException;
import com.example.seebook.global.restclient.AladinComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AladinComponent aladinComponent;
    private final String TTB_KEY ="ttbwhaud15971218001";

    public BookListResponseDTO findBookByText(String Query, String QueryType, int start) {
        return BookListResponseDTO.form(aladinComponent.findAllByQuery(TTB_KEY, Query, QueryType, "js", start, 20131101));
    }

    public void findDetailBook(String isbn) {
//        사용자가 책 리스트에서 클릭해서 상세정보를 보려함. -- 만약 책의 리뷰하 한개인데 리뷰를 삭제시 처리방법?
//        1. isbn으로 DB조회 없으면 알리딘에 조회. 가져올때 댓글도 가져와야해서 중지하고 다른것부터만듦
        aladinComponent.findByIsbn13(TTB_KEY, isbn, "ISBN", "js", 20131101);
    }

    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(BookException.NotFoundBookException::new);
    }
}
