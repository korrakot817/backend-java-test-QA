package com.example.testProjectW.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.StandardException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class ValidateException extends Exception {

    private String message;
    private List<ErrorDetail> error;

    public ValidateException(String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;

    }

}
