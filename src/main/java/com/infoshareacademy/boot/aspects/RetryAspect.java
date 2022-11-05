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
public class RetryAspect {
    private static final Logger log = LoggerFactory.getLogger(RetryAspect.class);

    @Pointcut("@annotation(Retry)")
    private void annotated() {
    }

    @Around("annotated()")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object proceed = null;
        for (int i = 1; i < 4; i++) {
            proceed = proceedingJoinPoint.proceed();
            log.info("Try " + i + " produced " + proceed);
        }
        log.info("Returning " + proceed + " after 3 tries");
        return proceed;
    }
}