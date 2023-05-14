/*
  Wrap the whole user repository with a web layer using a controller layer (HTTP is the Platform)
*/

package com.user.todoozy.backend;

// import libraries
// annotations
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// import from other folders
import com.user.todoozy.backend.RuntimeException.NotFound.UserNotFoundException;
import com.user.todoozy.backend.RuntimeException.NotFound.UserIDNotFoundException;
import com.user.todoozy.backend.RuntimeException.NotFound.UserInformationNotFoundException;
import com.user.todoozy.backend.RuntimeException.BadRequest.RegistrationFormException;
import com.user.todoozy.backend.RuntimeException.Conflict.UserConflictException;
import com.user.todoozy.backend.helper_functions.Validate_User;
import com.user.todoozy.backend.model.User;
import com.user.todoozy.backend.service.UserService;

@RestController
@RequestMapping("/todoozy")
public class UserController {

    private static final BCryptPasswordEncoder password_encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    // Aggregate root
    // tag::get-aggregate-root[]
    // GET request
    @GetMapping("/user-account/get-all-users/")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    // end::get-aggregate-root[]

    // POST request
    @PostMapping("/user-account/create-account/")
    public ResponseEntity<?> saveUser(@RequestBody User new_user) {
        // helper function to validate the user input before inserting new user into the database
        String new_username = new_user.getUsername();
        String new_password = new_user.getPassword();
        String new_password_confirmation = new_user.getPassword_confirmation();
        String new_email = new_user.getEmail();
        HashMap<String, String> errors_map = new HashMap<String, String>();
        Validate_User registration_validation = new Validate_User(new_username, new_password, new_password_confirmation, new_email, errors_map);
        registration_validation.check_username();
        registration_validation.check_password();
        registration_validation.check_password_confirmation();
        registration_validation.check_email();

        // if there is no errors
        if(errors_map.isEmpty()) {

            // query the database to see if the username, password, email already been used
            User findUsername = userService.findUserByUsername(new_username);
            if(findUsername != null) {
                errors_map.put("username", "This username already exists");
                throw new UserConflictException(errors_map);
            }

            Boolean findPassword = userService.findByPassword(new_password);
            if(findPassword) {
                errors_map.put("password", "This password already exists");
                throw new UserConflictException(errors_map);
            }

            User findEmail = userService.findUserByEmail(new_email);
            if(findEmail != null) {
                errors_map.put("email", "This email already exists");
                throw new UserConflictException(errors_map);
            }

            // hash the password using bcrypt
            new_user.setPassword(password_encoder.encode(new_password));

            // save the user to the database
            userService.saveUser(new_user);
            return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
        }

        // throw the errors in registration form exception if there is an error in the form data
        throw new RegistrationFormException(errors_map);
    }

    // PATCH request
    @PatchMapping("/user-account/update-password/")
    public ResponseEntity<?> updateUser(@RequestBody String username, String password, String password_confirmation) {
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
            Boolean findPassword = userService.findByPassword(update_password); // check whether the password has existed
            if(findPassword) {
                errors_HashMap.put("password", "This password already exists");
                throw new UserConflictException(errors_HashMap);
            }

            User findUsername = userService.findUserByUsername(update_username);
            if(findUsername == null) {
                findUsername.setPassword(password_encoder.encode(update_password));
                findUsername.setPassword_confirmation(password_encoder.encode(update_password_confirmation));
                userService.saveUser(findUsername);
                return new ResponseEntity<>("User password updated successfully", HttpStatus.OK);
            }

            // abort 404 if no user found
            errors_HashMap.put("username", "Cannot find any user");
            throw new UserNotFoundException(errors_HashMap);
        }

        // throw the errors in the registration form exception if there an error in the form data
        throw new RegistrationFormException(errors_HashMap);
    }

    // DELETE request
    @DeleteMapping("/user-account/delete-username/")
    public ResponseEntity<?> deleteUserByUsername(@RequestBody String username) {
        HashMap<String, String> errors_map = new HashMap<>();

        String delete_username = username;

        // query the database to see if the user account exists
        Optional<User> findUsername = userService.findByUsername(delete_username);
        if(findUsername != null) {
            userService.deleteUserByUsername(delete_username);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }

        // throw not found error if no user is found
        errors_map.put("username", "No username found");
        throw new UserNotFoundException(errors_map);
    }

    @DeleteMapping("/user-account/delete-email/")
    public ResponseEntity<?> deleteUserByEmail(@RequestBody String email) {
        HashMap<String, String> errors_map = new HashMap<>();

        String delete_email = email;

        // query the database to get the user account with the email
        Optional<User> findEmail = userService.findByEmail(delete_email);
        if(findEmail != null) {
            userService.deleteUserByEmail(delete_email);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }

        // throw not found error if no user is found
        errors_map.put("email", "No email found");
        throw new UserNotFoundException(errors_map);
    }

    // Single user control
    // get one user
    @GetMapping("/user-account/get-id/{id}/")
    public User getUserById(@PathVariable String id) {
        // convert the id from string to UUID
        UUID string_to_uuid = UUID.fromString(id);
        return userService.findById(string_to_uuid)
                .orElseThrow(() -> new UserIDNotFoundException(string_to_uuid)); // this is for UUID only
    }

    @GetMapping("/user-account/get-username/{username}/")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .orElseThrow(() -> new UserInformationNotFoundException("username", username));
    }

    @GetMapping("/user-account/get-email/{email}/")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .orElseThrow(() -> new UserInformationNotFoundException("email", email));
    }


    // put one user
    @PutMapping("/user-account/change-one-user/{id}/")
    User replaceUser(@RequestBody User newUser, @PathVariable String id) {
        // convert string to uuid
        UUID uuid_from_string = UUID.fromString(id);
        return userService.findById(uuid_from_string)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setPassword_confirmation(newUser.getPassword_confirmation());
                    user.setEmail(newUser.getEmail());
                    return userService.saveUser(user);
                })
                .orElseGet(() -> {  
                    newUser.setId(uuid_from_string);
                    return userService.saveUser(newUser);
                });
    }

}
