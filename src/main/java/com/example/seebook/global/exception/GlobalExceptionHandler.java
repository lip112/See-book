package com.example.seebook.global.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>  processValidationError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(builder.toString(), e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> processIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("IllegalArgumentException", e.getMessage()));
    }
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> processUserException(UserException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getErrorCode().name(), e.getMessage()));
    }
    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<ErrorResponse> processReviewException(ReviewException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getErrorCode().name(), e.getMessage()));
    }
    @ExceptionHandler(BookException.class)
    public ResponseEntity<ErrorResponse> processBookException(BookException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getErrorCode().name(), e.getMessage()));
    }
    @ExceptionHandler(S3Exception.class)
    public ResponseEntity<ErrorResponse> processS3Exception(S3Exception e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getErrorCode().name(), e.getMessage()));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> processS3Exception(JwtException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getErrorCode().name(), e.getMessage()));
    }

    @ExceptionHandler(ReportException.class)
    public ResponseEntity<ErrorResponse> processReportException(ReportException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getErrorCode().name(), e.getMessage()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.toString(), e.getMessage()));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(HttpMessageNotReadableException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.toString(), e.getMessage()));
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<ErrorResponse> processDateTimeException(DateTimeException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("DateTimeException", e.getMessage()));
    }

    @Getter
    @AllArgsConstructor
    private class ErrorResponse {
        private String code;
        private String message;
    }
}
