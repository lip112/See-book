package com.example.seebook.global.exception;

import lombok.Getter;

@Getter
public class ReportException extends RuntimeException{
    private final ErrorCode errorCode;

    public ReportException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static class NotFoundReportIdException extends ReportException{
        public NotFoundReportIdException(){
            super(ErrorCode.NOT_FOUND_REPORT);
        }
    }
}
