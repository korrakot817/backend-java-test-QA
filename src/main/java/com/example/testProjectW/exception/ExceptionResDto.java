package com.example.testProjectW.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResDto {

    private String message;

    private List<ErrorDetail> error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorDetail> getError() {
        return error;
    }

    public void setError(List<ErrorDetail> error) {
        this.error = error;
    }
}
