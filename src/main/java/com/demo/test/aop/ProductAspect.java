package com.demo.test.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The  Product aspect used for logging request and response.
 */
@Aspect
@Component
@Slf4j
public class ProductAspect {


    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired(required = false)
    private HttpServletResponse response;

    /**
     * Before advice.
     *
     * @param joinPoint the join point
     * @param object    the object
     */
    @Before(value = "execution(* com.demo.test.controller.ProductController.*(..)) and args(object)")
    public void beforeAdvice(JoinPoint joinPoint, Object object) {

        log.debug("============================request begin==========================================");
        log.debug("URI         : " + request.getRequestURI());
        log.debug("Method      : " + request.getMethod());
        log.debug("Request body: " + object.toString());
        log.debug("============================request end==========================================");

    }

    /**
     * After returning advice.
     *
     * @param object the object
     */
    @AfterReturning(pointcut="execution(* com.demo.test.controller.ProductController.*(..))", returning="object")
    public void afterReturningAdvice(Object object) {
        log.debug("============================response begin==========================================");
        log.debug("Response body: " + object.toString());
        log.debug("Status code  : " + response.getStatus());
        log.debug("============================response end==========================================");

    }

    /**
     * After advice.
     *
     * @param exception the exception
     */
    @AfterThrowing(pointcut="execution(* com.demo.test.controller.ProductController.*(..))", throwing="exception")
    public void afterAdvice(Exception exception) {
        log.debug("============================response begin==========================================");
        log.debug("Response body: " + exception.getMessage());
        log.debug("Status code  : " + response.getStatus());
        log.debug("============================response end==========================================");
    }




}