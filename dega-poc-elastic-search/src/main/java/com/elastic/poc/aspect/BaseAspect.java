package com.elastic.poc.aspect;

import com.elastic.poc.exception.ValidationFailedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Slf4j
public abstract class BaseAspect {
    private final ObjectMapper mapper;

    protected Object log(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Instant start = Instant.now();

        final MethodSignature method = (MethodSignature) joinPoint.getSignature();

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (final ValidationFailedException validationFailedException) {
            try {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            } catch (Exception e) {
                log.info("NON Transactional Marked Method");
            }
        } catch (final Exception exception) {

        }
        final Instant end = Instant.now();
        final Duration time = Duration.between(start, end);
        log.info("Method Name: " + method.getMethod().getName() + "  ,  " + "Time in Milli Seconds: " + time.toMillis());
        MDC.clear();
        return proceed;
    }
}
