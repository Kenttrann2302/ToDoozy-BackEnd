package com.user.todoozy.backend.RuntimeException.BadRequest;

// import libraries
import java.util.HashMap;

// this is file for handling the 400 bad request in the registration form
public class RegistrationFormException extends RuntimeException {

    private final HashMap<String, String> errors_map;

    // data constructor
    public RegistrationFormException(HashMap<String, String> errors_map) {
        this.errors_map = errors_map;
    }

    public HashMap<String, String> getErrorsMap() {
        return this.errors_map;
    }
    
}
