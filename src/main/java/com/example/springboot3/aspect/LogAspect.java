package com.example.springboot3.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Before("execution(* *..*.*UserService.*(..))")
    public void startLog(JoinPoint joinPoint) {
        log.info("method start:" + joinPoint.getSignature());
    }

    @After("execution(* *..*.*UserService.*(..))")
    public void endLog(JoinPoint joinPoint) {
        log.info("method end:" + joinPoint.getSignature());
    }

    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {
        log.info("method start:" + jp.getSignature());

        try {
            Object result = jp.proceed();
            log.info("method end:" + jp.getSignature());
            return result;
        } catch (Exception e) {
            log.error("method Exception:" + jp.getSignature());
            throw e;
        }
    }
}
