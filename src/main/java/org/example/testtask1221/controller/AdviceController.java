package org.example.testtask1221.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;
import org.example.testtask1221.dto.SystemError;
import org.example.testtask1221.dto.ValidationErrorResponse;
import org.example.testtask1221.dto.ViolationDto;
import org.example.testtask1221.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<SystemError> handleEntityNotFoundException(EntityNotFoundException e) {
    var error = getSystemError(e);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    final List<ViolationDto> violations = e.getBindingResult().getFieldErrors().stream()
        .map(error -> new ViolationDto(error.getField(), error.getDefaultMessage()))
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorResponse(violations));
  }

  private SystemError getSystemError(Exception e) {
    return new SystemError()
        .setMessage(e.getMessage())
        .setStackTrace(getStackTrace(e));
  }

  private String getStackTrace(Throwable ex) {
    Writer strWr = new StringWriter();
    PrintWriter wr = new PrintWriter(strWr);
    ex.printStackTrace(wr);
    return strWr.toString();
  }
}
