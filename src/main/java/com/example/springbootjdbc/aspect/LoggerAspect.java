package com.example.springbootjdbc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    @Around("execution(* com.example.springbootjdbc..*.*(..))")
    public Object log(ProceedingJoinPoint jp) throws Throwable{
        log.info(jp.getSignature().toString()+"Method execution start");
        Instant start = Instant.now();
        Object returnObj = jp.proceed();
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        log.info("Time took to execute"+jp.getSignature().toString()+" method is  "+timeElapsed+" ms");
        log.info(jp.getSignature().toString()+" method execution end");
        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.example.springbootjdbc.*.*(..))",throwing = "ex")
    public void logException(JoinPoint jp,Exception ex){
        log.error(jp.getSignature()+" An exception happened due to : "+ex.getMessage());
    }
}
