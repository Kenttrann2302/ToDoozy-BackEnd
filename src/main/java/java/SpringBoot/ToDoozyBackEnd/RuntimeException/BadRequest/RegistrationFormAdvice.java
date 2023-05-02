package java.SpringBoot.ToDoozyBackEnd.RuntimeException.BadRequest;

// import libraries
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


// this is a file to give Java the advice to handle the RegistrationFormException 400 Bad Request
@ControllerAdvice
public class RegistrationFormAdvice {
    
    @ResponseBody
    @ExceptionHandler(RegistrationFormException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    HashMap<String, String> registrationFormHandler (RegistrationFormException ex) {
        return ex.getErrorsMap();
    }
}
