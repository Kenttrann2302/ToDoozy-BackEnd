package java.SpringBoot.ToDoozyBackEnd.RuntimeException.NotFound;

// import libraries
import java.util.HashMap;

// this is a file for handling the 404 not found when the user is not found with the system
public class UserNotFoundException extends RuntimeException {

    private final HashMap<String, String> errors_map;

    // data constructor
    public UserNotFoundException(HashMap<String, String> errors_map) {
        this.errors_map = errors_map;
    }

    public HashMap<String, String> getErrors_map() { return this.errors_map; }
}
