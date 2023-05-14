package com.user.todoozy.backend.load_db;

// load some sample users
// import libraries
import com.user.todoozy.backend.model.User;
import com.user.todoozy.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {

        return args -> {
            log.info("Preloading" + userRepository.save(new User("kenttran", "fnifni23$me", "fnifni23$me", "fcwefnw@gmail.com")));
            log.info("Preloading" + userRepository.save(new User("helloworld", "neifnwfi223$%%", "neifnwfi223$%%", "eweefwfmw@gmail.com")));
        };
    }
}
