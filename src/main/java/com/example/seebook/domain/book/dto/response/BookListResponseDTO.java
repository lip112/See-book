package com.example.seebook.domain.book.dto.response;

import com.example.seebook.domain.book.dto.BookDTO;
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
    private List<BookDTO> book;


    public static BookListResponseDTO from(Map<String, Object> map) {
        List<BookDTO> books = new ArrayList<>();
        List<Map<String, String>> item1 = (List<Map<String, String>>) map.get("item");
        for (Map<String, String> bookData : item1) {
            BookDTO book = BookDTO.builder()
                    .title(bookData.get("title"))
                    .buyLink(bookData.get("link"))
                    .author(bookData.get("author"))
                    .description(bookData.get("description"))
                    .isbn13(bookData.get("isbn13"))
                    .imageLink(bookData.get("cover"))
                    .categoryId(String.valueOf(bookData.get("categoryId")))
                    .categoryName(bookData.get("categoryName").substring(0, bookData.get("categoryName").lastIndexOf(">")))
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
}
