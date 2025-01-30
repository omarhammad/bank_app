package com.omarhammad.loans.exceptions;

import com.omarhammad.loans.controllers.dtos.ErrorResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger;

    public GlobalExceptionHandler() {
        this.logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException exception,
                                                                          WebRequest webRequest) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDTO(
                                webRequest.getDescription(false),
                                HttpStatus.NOT_FOUND,
                                exception.getMessage(),
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(InvalidMobileNumberException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidMobileNumberException(InvalidMobileNumberException exception,
                                                                               WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponseDTO(
                                webRequest.getDescription(false),
                                HttpStatus.BAD_REQUEST,
                                exception.getMessage(),
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                                  WebRequest webRequest) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponseDTO(
                                webRequest.getDescription(false),
                                HttpStatus.BAD_REQUEST,
                                exception.getMessage(),
                                LocalDateTime.now()
                        )
                );
    }

}
