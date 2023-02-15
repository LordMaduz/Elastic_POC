package com.elastic.poc.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogInputApiAspect extends BaseAspect {
    public LogInputApiAspect(ObjectMapper mapper) {

        super(mapper);
    }

    @Around("@annotation(com.elastic.poc.annotation.LogInputApi)")
    public Object logInPut(final ProceedingJoinPoint joinPoint) throws Throwable {
        return log(joinPoint);
    }
}
