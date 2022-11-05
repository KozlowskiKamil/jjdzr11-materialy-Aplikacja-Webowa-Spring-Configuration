package com.infoshareacademy.boot.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogBookMapperAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LogBookMapperAspect.class);

    @Pointcut("execution(public * com.infoshareacademy.boot.mapper.BookMapper.*(..))")
    private void mapperCalled() {
    }

    @Before("mapperCalled()")
    public void logMapperUsage() {
        LOG.info("BookMapper is called");
    }

    @AfterReturning(pointcut = "mapperCalled()", returning = "value")
    public void logResult(Object value) {
        LOG.info("BookMapper produced: " + value);
    }

    @AfterThrowing(pointcut = "mapperCalled()", throwing = "ex")
    public void logNullPointerException(NullPointerException ex) {
        LOG.info("BookMapper failed: " + ex.getMessage());
    }

    @After("mapperCalled()")
    public void logAfterMapperUsage() {
        LOG.info("BookMapper was called");
    }

}
