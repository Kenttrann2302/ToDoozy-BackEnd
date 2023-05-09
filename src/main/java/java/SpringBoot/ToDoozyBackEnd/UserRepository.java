/*
 By declaring the UserRepository interface, we can do the following for our REST API:
 + Create new user
 + Find the user (one, all, or search by simple or complex properties)
 + Update information for existing user
 + Update existing user with new user (not really in use)
 + Delete an existing user
*/

package java.SpringBoot.ToDoozyBackEnd;

// import libraries
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findByUsername(String username);
    public List<User> findByPassword(String password);
    public List<User> findByEmail(String email);

    // deletion
    public void deleteUserBy(String username);
}
