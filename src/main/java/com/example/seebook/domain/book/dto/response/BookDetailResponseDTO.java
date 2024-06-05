package com.example.seebook.domain.book.dto.response;

import com.example.seebook.domain.book.domain.Book;
import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.review.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class BookDetailResponseDTO {

    private String reviewCount;
    private int endPage;
    private BookDTO book;
    private List<ReviewDTO> review;

    public static BookDetailResponseDTO form(String reviewCount, Book bookInfo, List<ReviewDTO> review) {
        BookDTO book = BookDTO.builder()
                .title(bookInfo.getTitle())
                .buyLink(bookInfo.getBuyLink())
                .author(bookInfo.getAuthor())
                .description(bookInfo.getDescription())
                .isbn13(bookInfo.getIsbn13())
                .imageLink(bookInfo.getImageLink())
                .categoryId(bookInfo.getCategoryId())
                .categoryName(bookInfo.getCategoryName())
                .publisher(bookInfo.getPublisher())
                .build();
        return BookDetailResponseDTO.builder()
                .book(book)
                .reviewCount(reviewCount)
                .endPage(Integer.parseInt(reviewCount) / 10 + 1)
                .build();
    }
//    public static BookDetailResponseDTO form(String reviewCount, Map<String, Object> bookInfo, List<Review> review) {
//        Book book = Book.builder()
//                .title(bookInfo.get("title").toString())
//                .link(bookInfo.get("link").toString())
//                .author(bookInfo.get("author").toString())
//                .pubDate(bookInfo.get("pubDate").toString())
//                .description(bookInfo.get("description").toString())
//                .isbn13(bookInfo.get("isbn13").toString())
//                .imageLink(bookInfo.get("cover").toString())
//                .categoryId(String.valueOf(bookInfo.get("categoryId")))
//                .categoryName(bookInfo.get("categoryName").toString())
//                .publisher(bookInfo.get("publisher").toString())
//                .build();
//        return BookDetailResponseDTO.builder()
//                .book(book)
//                .reviewCount(reviewCount)
//                .endPage(Integer.parseInt(reviewCount) / 10 + 1)
//                .build();
//    }

}
