package com.user.todoozy.backend.RuntimeException.NotFound;

// this is a file for handling the 404 not found with the given user id
public class UserInformationNotFoundException extends RuntimeException {
    public UserInformationNotFoundException(String type_user_information, String user_information) {
        super("Could not find user with " + type_user_information + ": " + user_information);
    }
}
