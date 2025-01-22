package com.omarhammad.loans.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger;

    public GlobalExceptionHandler() {
        this.logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }


}
