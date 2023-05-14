package com.user.todoozy.backend.service.impl;

// import other files
import com.user.todoozy.backend.model.User;
import com.user.todoozy.backend.repository.UserRepository;
import com.user.todoozy.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
// implement the student services class
public class UserServiceImpl implements UserService {

    private static final BCryptPasswordEncoder password_encoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() { return userRepository.findAll(); }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override // get request
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserByUsername(String username) { // post request
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Boolean findByPassword(String password) { // post request
        // check if the password entered has been used by other users
        List<User> all_users = userRepository.findAll();

        for(int i = 0; i < all_users.size(); i++) {
            boolean passwordMatch = password_encoder.matches(password, all_users.get(i).getPassword());
            if(passwordMatch) { // if password is found
                return true;
            }
        }

        return false; // if password is not found
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }
}
