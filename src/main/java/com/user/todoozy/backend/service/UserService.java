package com.user.todoozy.backend.service;

import com.user.todoozy.backend.model.User;

// import libraries
import java.util.List;
import java.util.Optional;

// a user service that the user controller can call
public interface UserService {
    List<User> findAll();

    Optional<User> findById(String id);

    Optional<User> findByUsername(String username); // for GET request

    User postfindByUsername(String username); // for POST request

    Boolean findByPassword(String password); // for POST request

    Optional<User> findByEmail(String email); // for GET request

    User postfindByEmail(String email); // for POST request

    User saveUser(User user);

    void deleteUserByUsername(String username);

    void deleteUserByEmail(String email);

}