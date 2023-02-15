package com.elastic.poc.exception.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

@Getter
public class InputValidationExceptionResponse extends RestExceptionResponse {

    private final Map<String, Set<String>> errors;

    @Builder
    public InputValidationExceptionResponse(final String host, final int errorCode,
                                            final HttpStatus httpStatus, final Instant timeStamp, final Map<String, Set<String>> errors) {
        super(host, errorCode, httpStatus, timeStamp);
        this.errors = errors;
    }
}

