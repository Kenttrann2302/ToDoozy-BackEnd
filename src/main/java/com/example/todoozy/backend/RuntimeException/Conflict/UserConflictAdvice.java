package com.example.todoozy.backend.RuntimeException.Conflict;



// import libraries
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


// this is the file to give Java the advice to handle the UserConflictException 409
@ControllerAdvice
public class UserConflictAdvice {

    @ResponseBody
    @ExceptionHandler(UserConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    HashMap<String, String> userConflictHandler(UserConflictException ex) {
        return ex.getErrorsMap();
    }
    
}
