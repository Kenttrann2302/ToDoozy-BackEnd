package com.example.todoozy.backend.RuntimeException.NotFound;

// this is a file for handling the 404 not found with the given user id
public class UserIDNotFoundException extends RuntimeException {
    public UserIDNotFoundException(String id) {
        super("Could not find user with user id: " + id);
    }
}
