package com.infoshareacademy.boot.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MeasureTimeAspect {
    private static final Logger log = LoggerFactory.getLogger(MeasureTimeAspect.class);

    @Pointcut("within(com.infoshareacademy.boot.mapper.ProductMapper)")
    private void productMapper() {
    }

    @Pointcut("@annotation(MeasureTime)")
    private void annotated() {
    }

    @Around("productMapper() || annotated()")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.nanoTime();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long endTime = System.nanoTime();
            log.info("invocation time = " + (endTime - startTime));
        }
    }
}