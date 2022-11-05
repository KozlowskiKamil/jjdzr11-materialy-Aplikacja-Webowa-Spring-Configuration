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
public class LogProductMapperAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LogProductMapperAspect.class);


    @Pointcut("execution(public * com.infoshareacademy.boot.mapper.ProductMapper.*(..))")
    private void mapperCalled() {
    }

    @Before("mapperCalled()")
    public void logMapperUsage() {
        LOG.info("ProductMapper is called");
    }

    @AfterReturning(pointcut = "mapperCalled()", returning = "value")
    public void logResult(Object value) {
        LOG.info("ProductMapper produced: " + value);
    }

    @AfterThrowing(pointcut = "mapperCalled()", throwing = "ex")
    public void logNullPointerException(NullPointerException ex) {
        LOG.info("ProductMapper failed: " + ex.getMessage());
    }

    @After("mapperCalled()")
    public void logAfterMapperUsage() {
        LOG.info("ProductMapper was called");
    }

}
