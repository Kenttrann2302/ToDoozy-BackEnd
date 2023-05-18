package com.user.todoozy.backend.model;

public enum Timezone {
    // Define the timezones
    UTC("Coordinated Universal Time"),
    PST("Pacific Standard Time"),
    EST("Eastern Standard Time"),;
    // Add more timezones as needed

    // Constructor
    private String displayTimeZone;

    Timezone(String displayTimeZone) {
        this.displayTimeZone = displayTimeZone;
    }

    // Getters
    public String getDisplayTimeZone() {
        return displayTimeZone;
    }
}
