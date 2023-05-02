/*
  Wrap the whole user repository with a web layer using a controller layer (HTTP is the Platform)
*/

package java.SpringBoot.ToDoozyBackEnd;

// import libraries
// annotations
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
import helper_functions.Validate_User;

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
    @GetMapping("/users/")
    List<User> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    // POST request
    @PostMapping("/users/")
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
                    throw UsernameExistException(new_username);
                }
            }

            for(User user : repository.findByPassword(new_password)
            ) {
                boolean passwordMatch = password_encoder.matches(new_password, user.getPassword());
                if(passwordMatch) {
                    errors_map.put("password", "This password already exists");
                    throw PasswordExistException(new_password);
                }
            }

            for(User user : repository.findByEmail(new_email)) {
                if(user.getEmail().length() > 0) {
                    errors_map.put("email", "This email already exists");
                    throw EmailExistException(new_email);
                }
            }

            // save the user to the database
            return repository.save(newUser);
        }

        // throw the errors in registration form exception if there is an error in the form data
        throw RegistrationFormException(newUser);
    }

    // update an user using PATCH request
    @PatchMapping("/users/")
    User updateUser(@RequestBody String username, String password, String password_confirmation) {

    }

    // Single user control
    // get one user
    @GetMapping("/users/{id}/")
    User one(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // put one user
    @PutMapping("/users/{id}/")
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
