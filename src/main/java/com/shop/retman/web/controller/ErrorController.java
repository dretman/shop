package com.shop.retman.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(Exception.class)
    public String processException(Exception e) {
        LOGGER.error("Exception catched: ", e);
        return "redirect:/error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String processNoHandlerFoundException(Exception e) {
        LOGGER.error("Exception catched NoHandlerFoundException ", e);
        return "redirect:/404";
    }

}