package org.example.practice15.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* org.example.practice15.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        // Выполнение метода
        Object proceed = joinPoint.proceed();
        
        long executionTime = System.currentTimeMillis() - start;
        logger.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
        
        return proceed;
    }
}
