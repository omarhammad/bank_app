package com.omarhammad.cards.exceptions;

import com.omarhammad.cards.controllers.dto.ErrorResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException exception,
                                                                          WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(
                        webRequest.getDescription(false),
                        HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        LocalDateTime.now()

                ));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(ConstraintViolationException exception,
                                                                               WebRequest webRequest) {
        String message = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessageTemplate)
                .reduce("", (acc, cv) -> acc.isEmpty() ? cv : acc + "; " + cv);

        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO(
                        webRequest.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        message,
                        LocalDateTime.now()
                ));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                                  WebRequest webRequest) {

        String message = getRequestBodyErrorMessage(exception.getBindingResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(
                        webRequest.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        message,
                        LocalDateTime.now()
                ));
    }

    private String getRequestBodyErrorMessage(BindingResult bindingResult) {

        return bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                .reduce("", (acc, error) -> acc.isEmpty() ? error : acc + "; " + error);

    }

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCardAlreadyExistsException(CardAlreadyExistsException exception,
                                                                             WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(
                        webRequest.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        exception.getMessage(),
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(InvalidCardTypeException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidCardTypeException(InvalidCardTypeException exception,
                                                                           WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(
                        webRequest.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        exception.getMessage(),
                        LocalDateTime.now()
                ));
    }


    @ExceptionHandler(InvalidPinCodeException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPinCode(InvalidPinCodeException exception,
                                                                 WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDTO(
                        webRequest.getDescription(false),
                        HttpStatus.UNAUTHORIZED,
                        exception.getMessage(),
                        LocalDateTime.now()
                ));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalExceptions(Exception exception, WebRequest webRequest) {

        return ResponseEntity.internalServerError()
                .body(new ErrorResponseDTO(
                        webRequest.getDescription(false),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        exception.getMessage(),
                        LocalDateTime.now()
                ));
    }

}
