package com.example.seebook.domain.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class BookListResponseDTO {
    private int totalBookCount;
    private int endPage;
    private List<Book> book;


    public static BookListResponseDTO form(Map<String, Object> map) {
        List<Book> books = new ArrayList<>();
        List<Map<String, String>> item1 = (List<Map<String, String>>) map.get("item");
        for (Map<String, String> bookData : item1) {
            Book book = Book.builder()
                    .title(bookData.get("title"))
                    .link(bookData.get("link"))
                    .author(bookData.get("author"))
                    .pubDate(bookData.get("pubDate"))
                    .description(bookData.get("description"))
                    .isbn13(bookData.get("isbn13"))
                    .imageLink(bookData.get("cover"))
                    .categoryId(String.valueOf(bookData.get("categoryId")))
                    .categoryName(bookData.get("categoryName"))
                    .publisher(bookData.get("publisher"))
                    .build();

            books.add(book);
        }
        return BookListResponseDTO.builder()
                .book(books)
                .totalBookCount(Integer.parseInt(map.get("totalResults").toString()))
                .endPage(Integer.parseInt(map.get("totalResults").toString()) / 10 + 1)
                .build();
    }

    @Getter
    private static class Book {
        private String title;
        private String link;
        private String author;
        private String pubDate;
        private String description;
        private String isbn13;
        private String imageLink;
        private String categoryId;
        private String categoryName;
        private String publisher;

        @Builder
        public Book(String title, String link, String author, String pubDate, String description, String isbn13, String imageLink, String categoryId, String categoryName, String publisher) {
            this.title = title;
            this.link = link;
            this.author = author;
            this.pubDate = pubDate;
            this.description = description;
            this.isbn13 = isbn13;
            this.imageLink = imageLink;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.publisher = publisher;
        }
    }
}
