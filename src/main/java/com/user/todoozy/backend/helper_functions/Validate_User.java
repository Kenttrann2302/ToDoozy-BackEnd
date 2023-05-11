// this is a helper function that using regular expressions to check for the validation in the registration form
package com.user.todoozy.backend.helper_functions;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validate_User {
    // private variables
    private String username;
    private String password;
    private String password_confirmation;
    private String email;
    private HashMap<String, String> errors_map;

    // constructor
    public Validate_User() {};

    // data constructor
    public Validate_User(String username, String password, String password_confirmation, String email, HashMap<String, String> errors_map) {
        this.username = username;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.email = email;
        this.errors_map = errors_map;
    }

    // getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, String> getErrors_map() {
        return getErrors_map();
    }

    // setter methods
    public void setUsername() {
        this.username = username;
    }

    public void setPassword() {
        this.password = password;
    }

    public void setPassword_confirmation() {
        this.password_confirmation = password_confirmation;
    }

    public void setEmail() {
        this.email = email;
    }

    public void setErrors_map() {
        this.errors_map = errors_map;
    }

    // check the username
    public void check_username() {
        // if no username enter
        if (this.username.length() == 0) {
            this.errors_map.put("username", "Please enter your username");
            return;
        }

        // if username length is smaller than 8 characters
        else if(this.username.length() < 8) {
            this.errors_map.put("username", "Username must have the length greater than 8 characters");
            return;
        }
    }

    // check the password
    public void check_password() {
        // if no password enter
        if(this.password.length() == 0) {
            this.errors_map.put("password", "Please enter your password");
            return;
        }

        // if the password has no digit
        Pattern digit_pattern = Pattern.compile(".*\\d.*");
        Matcher digit_matcher = digit_pattern.matcher(this.password);
        boolean digit_matchFound = digit_matcher.find();
        if(!digit_matchFound) {
            this.errors_map.put("password", "Password must contain a digit from 0 to 9");
            return;
        }

        // if the password has no character
        Pattern char_pattern = Pattern.compile(".*[a-zA-Z]+.*");
        Matcher char_matcher = char_pattern.matcher(this.password);
        boolean char_matchFound = char_matcher.find();
        if(!char_matchFound) {
            this.errors_map.put("password", "Password must contain at least one alpha character");
            return;
        }

        // if the password has no upper character
        Pattern upper_char_pattern = Pattern.compile(".*[A-Z]+.*");
        Matcher upper_char_matcher = upper_char_pattern.matcher(this.password);
        boolean upper_char_matchFound = upper_char_matcher.find();
        if(!upper_char_matchFound) {
            this.errors_map.put("password", "Password must contain at least one upper character");
            return;
        }

        // if the password has no lower character
        Pattern lower_char_pattern = Pattern.compile(".*[a-z]+.*");
        Matcher lower_char_matcher = lower_char_pattern.matcher(this.password);
        boolean lower_char_matchFound = lower_char_matcher.find();
        if(!lower_char_matchFound) {
            this.errors_map.put("password", "Password must contain at least one lower character");
            return;
        }

        // if the password does not have any special character
        Pattern special_char_pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher special_char_matcher = special_char_pattern.matcher(this.password);
        boolean special_char_matchFound = special_char_matcher.find();
        if(!special_char_matchFound) {
            this.errors_map.put("password", "Password must contain at least one special character");
            return;
        }

        // if the password is the same as username
        if(this.password == this.username) {
            this.errors_map.put("password", "Password must be different from the username");
            return;
        }
    }

    // check for the password confirmation
    public void check_password_confirmation() {
        // if no password confirmation entered
        if(this.password_confirmation.length() == 0) {
            this.errors_map.put("password_confirmation", "Please enter the password confirmation");
            return;
        }

        if(this.password_confirmation != this.password) {
            this.errors_map.put("password_confirmation", "Password confirmation must be the same as password");
            return;
        }
    }

    // check for regular expression in the email address
    // function to check the email input to see if the email is valid
    public void check_email() {
        // if there is no email enter
        if(this.email.length() == 0) {
            this.errors_map.put("email", "Please enter your email address");
            return;
        }

        // check for the pattern inside the email address
        Pattern email_pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher email_matcher = email_pattern.matcher(this.email);
        boolean email_matchFound = email_matcher.find();
        if(!email_matchFound) {
            this.errors_map.put("email", "Invalid email address");
            return;
        }
    }
}
