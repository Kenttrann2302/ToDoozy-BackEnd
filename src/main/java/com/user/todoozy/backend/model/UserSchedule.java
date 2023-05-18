package com.user.todoozy.backend.model;

// import libraries
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

// this is the users schedule entity in the database
@Entity(name = "users_schedule")
public class UserSchedule {

    private @Id @GeneratedValue (generator = "uuid2") @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator") UUID id;

    private String title; // title of the activity
    private String description; // a brief description of the activity
    private LocalDateTime startTime; // the start time of the activity
    private LocalDateTime endTime; // the end time of the activity
    private Location location; // the location of the event or activity
    private Boolean reminder; // whether the user choose to be reminded for this activity
    @ElementCollection
    private List<String> recurrence; // the list of the repetition that user want to set for this activity
    private LocalDateTime time_created; // user's time created

    // function set default value of time_created to current date and time
    @PrePersist
    public void prePersist() {
        time_created = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name="id")
    private User user;

    // data constructor
    public UserSchedule() {} // default constructor

    // data constructor
    public UserSchedule(String title, String description, LocalDateTime startTime, LocalDateTime endTime, Location location, Boolean reminder, User user) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.reminder = reminder;
        this.user = user;
    }

    // getter methods
    public UUID getId() { return this.id; }
    public String getTitle() { return this.title; }
    public LocalDateTime getStartTime() { return this.startTime; }
    public LocalDateTime getEndTime() { return this.endTime; }
    public Location getLocation() { return this.location; }
    public Boolean getReminder() { return this.reminder; }
    public List<String> getRecurrence() { return this.recurrence; }

    // setter methods
    public void setId(UUID id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setReminder(Boolean reminder) {
        this.reminder = reminder;
    }
    public void setRecurrence(List<String> recurrence) {
        this.recurrence = recurrence;
    }
    public void setTime_created(LocalDateTime time_created) {
        this.time_created = time_created;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // override methods
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof UserSchedule)) return false;
        UserSchedule userSchedule = (UserSchedule) o;
        return Objects.equals(this.id, userSchedule.id) && Objects.equals(this.title, userSchedule.title) && Objects.equals(this.startTime, userSchedule.startTime) && Objects.equals(this.endTime, userSchedule.endTime) && Objects.equals(this.location, userSchedule.location) && Objects.equals(this.reminder, userSchedule.reminder) && Objects.equals(this.recurrence, userSchedule.recurrence) && Objects.equals(this.time_created, userSchedule.time_created) && Objects.equals(this.user, userSchedule.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.description, this.startTime, this.endTime, this.location, this.reminder, this.recurrence, this.time_created, this.user);
    }

    @Override
    public String toString() {
        return String.format(
                "UserSchedule[id='%s', title='%s', startTime='%s', endTime='%s', location='%s', reminder='%s', recurrence='%s', time_created='%s', user='%s']", this.id, this.title, this.startTime, this.endTime, this.location, this.reminder, this.recurrence, this.time_created, this.user
        );
    }
}
