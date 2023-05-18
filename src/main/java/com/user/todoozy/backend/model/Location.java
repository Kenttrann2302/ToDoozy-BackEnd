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


    private String address_line_1; // main address
    private String address_line_2; // building, apartment,...
    private String city;
    private String province;
    private String country;
    private String postal_code;
    private Timezone timezone;

    // constructors
    Location() {};

    Location(String address_line_1, String address_line_2, String city, String province, String country, String postal_code, Timezone timezone) {
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postal_code = postal_code;
        this.timezone = timezone;
    }

    // getter methods
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

    // setter methods
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

    // override methods
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Location)) return false;
//        Location location = (Location) o;
//        return Objects.equals(this.address_line_1, location.address_line_1) && Objects.equals(this.address_line_2, location.address_line_2) && Objects.equals(this.city, location.city) && Objects.equals(this.province, location.province) && Objects.equals(this.country, location.country) && Objects.equals(this.postal_code, location.postal_code) && Objects.equals(this.timezone, location.timezone);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(this.address_line_1, this.address_line_2, this.city, this.province, this.country, this.postal_code, this.timezone);
//    }
//
//    @Override
//    public String toString() {
//        return String.format(
//          "Location[address_line_1='%s', address_line_2='%s', city='%s', province='%s', country='%s', postal_code='%s', timezone='%s']", this.address_line_1, this.address_line_2, this.city, this.province, this.country, this.postal_code, this.timezone
//        );
//    }
}
