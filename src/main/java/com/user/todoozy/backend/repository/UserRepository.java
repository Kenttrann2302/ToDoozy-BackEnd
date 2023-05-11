/*
 By declaring the UserRepository interface, we can do the following for our REST API:
 + Create new user
 + Find the user (one, all, or search by simple or complex properties)
 + Update information for existing user
 + Update existing user with new user (not really in use)
 + Delete an existing user
*/

package com.user.todoozy.backend.repository;

// import libraries
import java.util.List;
import java.util.Optional;

import com.user.todoozy.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public Optional<User> findById(String id);

    public Optional<User> findByUsername(String username); // get request

    public User postfindByUsername(String username); // post request

    public Boolean findByPassword(String password);

    public Optional<User> findByEmail(String email); // get request

    public User postfindByEmail(String email); // post request

    // deletion
    public void deleteUserByUsername(String username);

    public void deleteUserByEmail(String email);
}
