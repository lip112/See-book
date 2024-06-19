package com.example.seebook.domain.book.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.review.dto.ReviewDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookInReviewListResponseDTO {
    private Long reviewCount;
    private Long endPage;
    private boolean isWished;
    private List<ReviewDTO> review;
    private BookDTO book;

    public void addBookDTO(BookDTO bookDTO) {
        this.book = bookDTO;
    }

    public void changeWishlistStatus(boolean wished) {
        isWished = wished;
    }

    @Builder
    public BookInReviewListResponseDTO(Long reviewCount, boolean isWished, Long endPage, List<ReviewDTO> review, BookDTO book) {
        this.reviewCount = reviewCount;
        this.isWished = isWished;
        this.endPage = endPage;
        this.review = review;
        this.book = book;
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
                .book(book)
                .reviewCount(0L)
                .endPage(null)
                .review(null)
                .isWished(false)
                .build();
    }
}
