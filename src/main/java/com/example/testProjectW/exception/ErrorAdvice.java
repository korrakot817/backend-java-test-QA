package com.example.testProjectW.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ErrorAdvice {

    @Autowired
    HttpServletResponse httpServletResponse;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidateException.class)
    public ExceptionResDto responseValidationFail(ValidateException e) {
        e.printStackTrace();
        return ExceptionResDto.builder().message(e.getMessage()).error(e.getError()).build();

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionResDto responseNotFound(NotFoundException e) {
        return ExceptionResDto.builder()
                .message("data not found").build();

    }
}
