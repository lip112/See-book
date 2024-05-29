package com.example.seebook.domain.book.service;

import com.example.seebook.global.restclient.AladinComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final AladinComponent aladinComponent;
    private final String TTB ="ttbwhaud15971218001";

    public void findBookByText() {

    }

    public void findBookByImage() {

    }

    public void findDetailBook() {
        System.out.println(aladinComponent.findByIsbn13(TTB, "9788928519781", "ISBN", "js", 20131101));
    }
}
