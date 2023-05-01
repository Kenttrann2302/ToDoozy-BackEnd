// this is a user class
package java.SpringBoot.ToDoozyBackEnd;

// import libraries
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username; // user's name
    private String password; // user's password
    private String email; // user's email address

    public User() {
        this.id = UUID.randomUUID().toString(); // generate a new UUID
    } // default constructor

    // data constructor
    public User(String username, String password, String email) {
        this.id = UUID.randomUUID().toString(); // generate a new UUID
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // getter methods
    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    // setter methods
    public void setId(String id) {
        this.id = id;
    } // not necessary

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // override methods
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.username, user.username) && Objects.equals(this.password, user.password) && Objects.equals(this.email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.password, this.email);
    }

    @Override
    public String toString() {
        return String.format(
                "User[id='%s', username='%s', password='%s', email='%s']", id, username, password, email
        );
    }

}
