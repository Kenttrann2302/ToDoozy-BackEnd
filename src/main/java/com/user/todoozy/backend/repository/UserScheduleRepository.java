package com.user.todoozy.backend.repository;

// import libraries
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.user.todoozy.backend.model.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, String>{
    public Optional<UserSchedule> findById(UUID id);

    public Optional<UserSchedule> findByUserId(UUID user_id);

    public UserSchedule findOneByTitle(String title);  // get the schedule with the given title

    public UserSchedule findOneByDescription(String description); // get the schedule with the given description

    public UserSchedule findOneByStartTime(LocalDateTime time); // get the schedule with the given start time

    public UserSchedule findOneByEndTime(LocalDateTime time); // get the schedule with the given end time

    public UserSchedule findOneByLocation(String location); // get the schedule with the given

}
