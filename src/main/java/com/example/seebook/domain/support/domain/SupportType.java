package com.example.seebook.domain.support.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SupportType {
    EVENT,
    ACCOUNT,
    REVIEW,
    BOOK,
    BUG,
    SUGGESTION;
}
