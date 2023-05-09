package com.example.todoozy.backend.RuntimeException.InternalServerError;

// this is a class for the response to an internal server error
public class ErrorResponse {
    
    private int status;
    private String error_message;

    // default constructor
    public ErrorResponse() {};

    // data constructor
    public ErrorResponse(int status, String error_message) {
        this.status = status;
        this.error_message = error_message;
    }

    // getter methods
    public int getStatus() { return status; }

    public String getErrorMessage() { return error_message; }

    // setter methods
    public void setStatus(int status) { this.status = status; }

    public void setErrorMessage(String error_message) { this.error_message = error_message; }

}
