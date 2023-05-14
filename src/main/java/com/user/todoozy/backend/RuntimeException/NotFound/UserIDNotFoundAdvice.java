package com.user.todoozy.backend.RuntimeException.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserIDNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(UserIDNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String UserIDNotFoundHandler(UserIDNotFoundException ex) {
        return ex.getMessage();
    }
}
