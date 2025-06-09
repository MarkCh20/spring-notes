package com.example.notesapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoSuchElementException.class)
  public String handleNotFound(NoSuchElementException ex, Model model) {
    model.addAttribute("errorMessage", ex.getMessage());
    return "error";
  }

  @ExceptionHandler(Exception.class)
  public String handleGenericError(Exception ex, Model model) {
    model.addAttribute("errorMessage", "Unexpected error occurred: " + ex.getMessage());
    return "error";
  }
}
