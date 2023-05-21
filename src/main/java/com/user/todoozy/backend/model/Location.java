package com.user.todoozy.backend.model;

// import libraries
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// this is a class that keeps the location of the user
@Entity(name = "location")
public class Location {

    private @Id @GeneratedValue (generator = "uuid2") @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator") UUID id;
    private String address_line_1; // main address
    private String address_line_2; // building, apartment,...
    private String city;
    private String province;
    private String country;
    private String postal_code;
    private Timezone timezone;
    public LocalDateTime time_created;

    // function set default value of time created to current date and time
    @PrePersist
    public void prePersist() {
        time_created = LocalDateTime.now();
    }

    // constructors
    public Location() {};

    public Location(String address_line_1, String address_line_2, String city, String province, String country, String postal_code, Timezone timezone) {
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postal_code = postal_code;
        this.timezone = timezone;
    }


    // getter methods
    public UUID getId() {
        return this.id;
    }
    public String getAddress_line_1() {
        return this.address_line_1;
    }

    public String getAddress_line_2() {
        return this.address_line_2;
    }

    public String getCity() {
        return this.city;
    }

    public String getProvince() {
        return this.province;
    }

    public String getCountry() {
        return this.country;
    }

    public String getPostal_code() {
        return this.postal_code;
    }

    public Timezone getTimezone() {
        return this.timezone;
    }
    public LocalDateTime getTime_created() {
        return this.time_created;
    }

    // setter methods
    public void setId(UUID id) {
        this.id = id;
    }
    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }
    public void setTime_created(LocalDateTime time_created) {
        this.time_created = time_created;
    }

    // override methods
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(this.id, location.id) && Objects.equals(this.address_line_1, location.address_line_1) && Objects.equals(this.address_line_2, address_line_2) && Objects.equals(this.province, location.province) && Objects.equals(this.city, city) && Objects.equals(this.country, location.country) && Objects.equals(this.postal_code, location.postal_code) && Objects.equals(this.timezone, location.timezone) && Objects.equals(this.time_created, location.time_created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.address_line_1, this.address_line_2, this.province, this.city, this.postal_code, this.timezone, this.time_created);
    }

    @Override
    public String toString() {
        return String.format(
                "User[id='%s', address_line_1='%s', address_line_2='%s', province='%s, city='%s', country='%s', postal_code='%s', timezone='%s', time_created='%s']", this.id, this.address_line_1, this.address_line_2, this.province, this.city, this.country, this.postal_code, this.timezone, this.time_created
        );
    }
}
