package com.user.todoozy.backend.repository;

// import libraries
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.user.todoozy.backend.model.Location;
import com.user.todoozy.backend.model.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, String>{
    public Optional<UserSchedule> findById(UUID id);

    public Optional<UserSchedule> findByUserId(UUID user_id); // get the schedule by the user id

    public List<Optional<UserSchedule>> findAllByUserId(UUID user_id); // get all the schedules by the user id

    public UserSchedule findOneByTitle(String title);  // get the schedule with the given title

    public List<UserSchedule> findAllByTitle(String title); // get all the schedules with the given title

    public UserSchedule findOneByDescription(String description); // get the schedule with the given description

    public List<UserSchedule> findAllByDescription(String description); // get all the schedules with the given description

    public UserSchedule findOneByStartTime(LocalDateTime start_time); // get the schedule with the given start time

    public List<UserSchedule> findAllByStartTime(LocalDateTime start_time); // get all the schedules with the given start time

    public UserSchedule findOneByEndTime(LocalDateTime time); // get the schedule with the given end time

    public List<UserSchedule> findAllByEndTime(LocalDateTime end_time); // get all the schedules with the given end time

    public UserSchedule findOneByLocation(Location location); // get the schedule with the given location

    public List<UserSchedule> findAllByLocation(Location location); // get all the schedules with the given location

    public UserSchedule findOneByTimeCreated(LocalDateTime time_created); // get the schedule with the given time created

    public List<UserSchedule> findAllByTimeCreated(LocalDateTime time_created); // get all the schedules with the given time created

}
