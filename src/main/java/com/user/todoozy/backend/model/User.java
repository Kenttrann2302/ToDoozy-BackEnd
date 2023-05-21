// this is a user class
// we are using H2 database and access it through JPA
package com.user.todoozy.backend.model;

// import libraries
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// this is the users entity in the database
@Entity(name = "users")
public class User {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // to encode the password

    private @Id @GeneratedValue (generator = "uuid2") @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator") UUID id;
    private String username; // user's name
    private String password; // user's password
    private String password_confirmation; // user's password confirmation
    private String email; // user's email address
    private LocalDateTime time_created; // user's time created


    // function set default value of time_created to current date and time
    @PrePersist
    public void prePersist() {
        time_created = LocalDateTime.now();
    }

    public User() {} // default constructor

    // data constructor
    public User(String username, String password, String password_confirmation, String email) {
        this.username = username;
        this.password = encoder.encode(password);
        this.password_confirmation = encoder.encode(password_confirmation);
        this.email = email;
    }

    // getter methods
    public UUID getId() {
        return this.id;
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
    public void setId(UUID id) {
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

    // override methods
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.username, user.username) && Objects.equals(this.password, user.password) && Objects.equals(this.password_confirmation, user.password_confirmation) && Objects.equals(this.email, user.email) && Objects.equals(this.time_created, user.time_created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.password, this.password_confirmation, this.email, this.time_created);
    }

    @Override
    public String toString() {
        return String.format(
                "User[id='%s', username='%s', password='%s', password_confirmation='%s, email='%s', time_created='%s']", this.id, this.username, this.password, this.password_confirmation, this.email, this.time_created
        );
    }
}
