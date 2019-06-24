package me.siavash.wotd.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {InvalidDateException.class})
  protected ResponseEntity handleInvalidRange(RuntimeException ex, WebRequest request) {
    log.error("Invalid date range detected ", ex);
    return  ResponseEntity.badRequest().body("Invalid date range");
  }

  @ExceptionHandler(value = EntityNotFoundException.class)
  protected ResponseEntity handleResourceNotFoundException(RuntimeException ex, WebRequest request) {
    log.error("Resource not found! " , ex);
    return  ResponseEntity.notFound().build();
  }
}
