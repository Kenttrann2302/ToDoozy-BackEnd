package com.user.todoozy.backend.RuntimeException.NotFound;

// import libraries
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

// this is the file to give Java the advice to handle the single user not found
@ControllerAdvice
public class UserInformationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserInformationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserInformationNotFoundException ex) {
        return ex.getMessage();
    }
}
