package com.example.testProjectW.exception;

public class NotFoundException extends Exception {

    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
