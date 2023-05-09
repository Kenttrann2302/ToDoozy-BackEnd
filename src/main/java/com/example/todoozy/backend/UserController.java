/*
  Wrap the whole user repository with a web layer using a controller layer (HTTP is the Platform)
*/

package com.example.todoozy.backend;

// import libraries
// annotations
import com.example.todoozy.backend.RuntimeException.BadRequest.RegistrationFormException;
import com.example.todoozy.backend.RuntimeException.Conflict.UserConflictException;
import com.example.todoozy.backend.helper_functions.Validate_User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;

// import from other folders
import com.example.todoozy.backend.RuntimeException.NotFound.UserNotFoundException;
import com.example.todoozy.backend.RuntimeException.NotFound.UserIDNotFoundException;

@RestController
public class UserController {

    private final UserRepository repository;

    private static final BCryptPasswordEncoder password_encoder = new BCryptPasswordEncoder();

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    // GET request
    @GetMapping("/todoozy/user-account/")
    List<User> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    // POST request
    @PostMapping("/todoozy/user-account/")
    User newUser(@RequestBody User newUser) {
        // helper function to validate the user input before inserting new user into the database
        String new_username = newUser.getUsername();
        String new_password = newUser.getPassword();
        String new_password_confirmation = newUser.getPassword_confirmation();
        String new_email = newUser.getEmail();
        HashMap<String, String> errors_map = new HashMap<String, String>();
        Validate_User registration_validation = new Validate_User(new_username, new_password, new_password_confirmation, new_email, errors_map);
        registration_validation.check_username();
        registration_validation.check_password();
        registration_validation.check_password_confirmation();
        registration_validation.check_email();

        // if there is no errors
        if(errors_map.isEmpty()) {
            // query the database to see if the username, password, email already been used
            for(User user : repository.findByUsername(new_username)) {
                if(user.getUsername().length() > 0) {
                    errors_map.put("username", "This username already exists");
                    throw new UserConflictException(errors_map);
                }
            }

            for(User user : repository.findByPassword(new_password)
            ) {
                boolean passwordMatch = password_encoder.matches(new_password, user.getPassword());
                if(passwordMatch) {
                    errors_map.put("password", "This password already exists");
                    throw new UserConflictException(errors_map);
                }
            }

            for(User user : repository.findByEmail(new_email)) {
                if(user.getEmail().length() > 0) {
                    errors_map.put("email", "This email already exists");
                    throw new UserConflictException(errors_map);
                }
            }

            // save the user to the database
            return repository.save(newUser);
        }

        // throw the errors in registration form exception if there is an error in the form data
        throw new RegistrationFormException(errors_map);
    }

    // PATCH request
    @PatchMapping("/todoozy/user-account/")
    User updateUser(@RequestBody String username, String password, String password_confirmation) {
        // validate the form data that the client sends
        // initiate a hash map to store the errors after validating the user's registration form
        HashMap<String, String> errors_HashMap = new HashMap<>();

        String update_username = username;
        String update_password = password;
        String update_password_confirmation = password_confirmation;

        Validate_User update_user_validation = new Validate_User(update_username, update_password, update_password_confirmation, null, errors_HashMap);
        update_user_validation.check_username();
        update_user_validation.check_password();
        update_user_validation.check_password_confirmation();

        // if there are no errors in the update form
        if(errors_HashMap.isEmpty()) {
            // query the database to see if the user account exists in the database

            for(User user : repository.findByPassword(update_password)) {
                // check if the password entered has been used by other users
                boolean passwordMatch = password_encoder.matches(update_password, user.getPassword());
                if(passwordMatch) {
                    errors_HashMap.put("password", "This password already exists");
                    throw new UserConflictException(errors_HashMap);
                }
            }

            for(User user : repository.findByUsername(update_username)) {
                // update the user password if the user is found in the database
                if(user != null) {
                    user.setPassword(update_password);
                    user.setPassword_confirmation(update_password_confirmation);
                    return repository.save(user);
                }
            }

            // abort 404 if no user found
            errors_HashMap.put("username", "Cannot find any user");
            throw new UserNotFoundException(errors_HashMap);
        }

        // throw the errors in the registration form exception if there an error in the form data
        throw new RegistrationFormException(errors_HashMap);
    }

    // DELETE request
    @DeleteMapping("/todoozy/user-account/")
    void deleteUser(@RequestBody String username) {
        HashMap<String, String> errors_map = new HashMap<>();

        String delete_username = username;

        // query the database to see if the user account exists
        for(User user : repository.findByUsername(username)) {
            // delete the user if the username is found
            if(user != null) {
                repository.deleteUserBy(delete_username);
                return;
            }
        }

        // throw not found error if no user is found
        errors_map.put("username", "No username found");
        throw new UserNotFoundException(errors_map);
    }

    // Single user control
    // get one user
    @GetMapping("/todoozy/users/{id}/")
    User one(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserIDNotFoundException(id));
    }

    // put one user
    @PutMapping("/todoozy/users/{id}/")
    User replaceUser(@RequestBody User newUser, @PathVariable String id) {
        return repository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setPassword_confirmation(newUser.getPassword_confirmation());
                    user.setEmail(newUser.getEmail());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

}
