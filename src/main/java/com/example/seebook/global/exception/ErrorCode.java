package com.example.seebook.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //User
    PHONE_NUMBER_DUPLICATION(HttpStatus.BAD_REQUEST, "이미 가입된 핸드폰 번호 입니다."),
    NOT_FOUND_EMAIL(HttpStatus.BAD_REQUEST, "찾을수 없는 이메일 입니다"),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "찾을수 없는 계정 입니다"),
    NOT_FOUND_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "찾을수 없는 전화번호 입니다"),
    LOGIN_FAILED(HttpStatus.BAD_REQUEST, "아이디 및 비밀번호가 일치하지 않습니다."),

   //review
   NOT_FOUND_REVIEW(HttpStatus.BAD_REQUEST, "찾을수 없는 리뷰입니다"),

    //Book
    NOT_FOUND_BOOK(HttpStatus.BAD_REQUEST, "찾을수 없는 책입니다");

    private final HttpStatus ErrorCode;
    private final String message;

}
