package com.example.seebook.domain.main.controller;

import com.example.seebook.domain.book.dto.BookDTO;
import com.example.seebook.domain.book.service.BookService;
import com.example.seebook.domain.event.dto.EventDTO;
import com.example.seebook.domain.event.service.EventService;
import com.example.seebook.domain.main.dto.response.CategoryResponseDTO;
import com.example.seebook.domain.main.dto.response.JoinMainPageResponseDTO;
import com.example.seebook.domain.main.dto.response.NewBookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {
    private final BookService bookService;
    private final EventService eventService;

    @GetMapping("/join")
    public ResponseEntity<JoinMainPageResponseDTO> getMainPage(){
        List<EventDTO> mainEventList = eventService.getMainEventList();
        List<BookDTO> newBooks = bookService.getMainNewBooks();
        List<JoinMainPageResponseDTO.BookWithReview> bestBooks = bookService.getBestBooks();

        JoinMainPageResponseDTO joinMainPageResponseDTO = JoinMainPageResponseDTO.builder()
                .eventCount(mainEventList.size())
                .event(mainEventList)
                .newBook(newBooks)
                .bestBook(bestBooks)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(joinMainPageResponseDTO);
    }

    @GetMapping("/new-book-list")
    public ResponseEntity<NewBookResponseDTO> getNewBookList(@RequestParam("page") int page, @RequestParam("bookId") Long bookId){
        bookService.getPlusNewBooks(page, bookId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryResponseDTO> getCategoryBooks(@RequestParam("categoryName") String categoryName,
                                                               @RequestParam("page") int page){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.findCategoryBooks(categoryName, page));
    }
}
