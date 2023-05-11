package com.user.todoozy.backend.RuntimeException.NotFound;


// import libraries
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// this is the file to give Java the advice to handle the UserNotFoundException 404
@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    HashMap<String, String> userNotFoundHandler(UserNotFoundException ex) { return ex.getErrors_map(); }

}
