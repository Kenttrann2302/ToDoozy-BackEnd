package com.user.todoozy.backend.dto;

// import libraries
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class UserDTO {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // to encode the password

    private String id;
    private String username;
    private String password;
    private String password_confirmation;
    private String email;
    private LocalDateTime time_created;

    public UserDTO() {
        this.id = UUID.randomUUID().toString(); // generate a new UUID
    }

    public UserDTO(String id, String username, String password, String password_confirmation, String email, LocalDateTime time_created) {
        this.id = UUID.randomUUID().toString(); // generate a new UUID
        this.username = username;
        this.password = encoder.encode(password);
        this.password_confirmation = encoder.encode(password_confirmation);
        this.email = email;
        this.time_created = time_created;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPassword_confirmation() { return this.password_confirmation; }

    public String getEmail() { return this.email; }

    public LocalDateTime getTime_created() { return this.time_created; }

    // setter methods
    public void setId(String id) {
        this.id = id;
    } // not necessary

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = encoder.encode(password);
    }

    public void setPassword_confirmation(String password_confirmation) { this.password_confirmation = encoder.encode(password_confirmation); }

    public void setEmail(String email) { this.email = email; }

    public void setTime_created(LocalDateTime time_created) { this.time_created = time_created; }

}
