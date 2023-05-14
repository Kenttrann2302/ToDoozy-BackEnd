package com.user.todoozy.backend.RuntimeException.NotFound;

import java.util.UUID;

// this is a special exception for handling not found id of the user
public class UserIDNotFoundException extends RuntimeException {
    public UserIDNotFoundException(UUID user_id) {
        super("Could not find user with id: " + user_id.toString());
    }   
}
