package com.user.todoozy.backend.RuntimeException.Conflict;

// import libraries
import java.util.HashMap;

// this is a file for handling the 409 conflict when the username, password and email is already exists within the database
public class UserConflictException extends RuntimeException {

    private final HashMap<String, String> errors_map;

    // data constructor
    public UserConflictException(HashMap<String, String> errors_map) {
        this.errors_map = errors_map;
    }

    public HashMap<String, String> getErrorsMap() {
        return this.errors_map;
    }

}