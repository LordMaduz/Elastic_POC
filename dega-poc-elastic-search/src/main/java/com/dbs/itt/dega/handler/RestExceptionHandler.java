package com.dbs.itt.dega.handler;


import com.dbs.itt.dega.exception.ValidationFailedException;
import com.dbs.itt.dega.exception.response.InputValidationExceptionResponse;
import com.dbs.itt.dega.exception.response.RestExceptionResponse;
import com.dbs.itt.dega.exception.response.MissingDataExceptionResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.InetAddress;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestExceptionResponse> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception) {

        Map<String, Set<String>> errorsMap = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())));

        return ResponseEntity
                .ok(getInputValidationExceptionResponse(errorsMap, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<RestExceptionResponse> handleMissingServletRequestParameterException(
            final MissingServletRequestParameterException exception) {

        return ResponseEntity
                .ok(getMissingDataExceptionResponse(new ValidationFailedException(exception.getMessage()), HttpStatus.BAD_REQUEST));

    }

    @ExceptionHandler(value = ValidationFailedException.class)
    public ResponseEntity<RestExceptionResponse> handleMissingData(
            final ValidationFailedException exception) {
        return ResponseEntity.ok(getMissingDataExceptionResponse(exception, HttpStatus.NO_CONTENT));
    }


    @SneakyThrows
    private RestExceptionResponse getMissingDataExceptionResponse(final RuntimeException exception,
                                                                  final HttpStatus status) {
        return MissingDataExceptionResponse.builder()
                .host(InetAddress.getLocalHost().getHostName())
                .message(exception.getMessage())
                .errorCode(status.value())
                .httpStatus(status)
                .timeStamp(Instant.now())
                .build();
    }

    @SneakyThrows
    private RestExceptionResponse getInputValidationExceptionResponse(
            final Map<String, Set<String>> errorsMap,
            final HttpStatus status) {
        return InputValidationExceptionResponse.builder()
                .host(InetAddress.getLocalHost().getHostName())
                .errors(errorsMap)
                .errorCode(status.value())
                .httpStatus(status)
                .timeStamp(Instant.now())
                .build();
    }
}


