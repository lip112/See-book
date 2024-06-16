package com.example.seebook.domain.review.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.dto.response.BookListResponseDTO;
import com.example.seebook.domain.review.dto.ReviewDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookInReviewListResponseDTO {
    private Long reviewCount;
    private Long endPage;
    private List<ReviewDTO> review;
    private BookDTO bookDTO;

    public void addBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    @Builder
    public BookInReviewListResponseDTO(Long reviewCount, Long endPage, List<ReviewDTO> review, BookDTO bookDTO) {
        this.reviewCount = reviewCount;
        this.endPage = endPage;
        this.review = review;
        this.bookDTO = bookDTO;
    }

    public static BookInReviewListResponseDTO fromMap(Map<String, Object> map) {
        List<Map<String, String>> item1 = (List<Map<String, String>>) map.get("item");
        Map<String, String> bookData = item1.get(0);

        BookDTO book = BookDTO.builder()
                    .title(bookData.get("title"))
                    .buyLink(bookData.get("link"))
                    .author(bookData.get("author"))
                    .description(bookData.get("description"))
                    .isbn13(bookData.get("isbn13"))
                    .imageLink(bookData.get("cover"))
                    .categoryId(String.valueOf(bookData.get("categoryId")))
                    .categoryName(bookData.get("categoryName"))
                    .publisher(bookData.get("publisher"))
                    .build();

        return BookInReviewListResponseDTO.builder()
                .bookDTO(book)
                .reviewCount(0L)
                .endPage(null)
                .review(null)
                .build();
    }
}
