package com.elastic.poc.exception.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
public class MissingDataExceptionResponse extends RestExceptionResponse {

    private final String message;

    @Builder
    public MissingDataExceptionResponse(final String host, final int errorCode,
                                        final HttpStatus httpStatus, final Instant timeStamp, final String message) {
        super(host, errorCode, httpStatus, timeStamp);
        this.message = message;
    }
}

