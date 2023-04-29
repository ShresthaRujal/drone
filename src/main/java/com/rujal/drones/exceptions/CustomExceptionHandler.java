package com.rujal.drones.exceptions;

import static com.rujal.drones.utils.Constants.Commons.VALIDATION_MESSAGE;
import static com.rujal.drones.utils.Response.failure;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.rujal.drones.utils.CustomFieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  // Handle Validation exception
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode code, WebRequest request) {
    return new ResponseEntity<>(failure(ex.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> new CustomFieldError(
            fieldError.getField(), fieldError.getDefaultMessage())
        ).toList(), VALIDATION_MESSAGE), BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
    return new ResponseEntity<>(failure(e.getMessage()), BAD_REQUEST);
  }


}
