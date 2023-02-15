package com.elastic.poc.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class RestExceptionResponse {

    private final String host;

    private final int errorCode;

    private final HttpStatus httpStatus;

    private final Instant timeStamp;
}
