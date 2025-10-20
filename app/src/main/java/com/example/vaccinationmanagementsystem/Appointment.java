package com.example.vaccinationmanagementsystem;

public class Appointment {
    private String id;
    private String vaccineType;
    private String date;
    private String time;
    private String location;
    private String status;

    public Appointment(String id, String vaccineType, String date, String time, String location, String status) {
        this.id = id;
        this.vaccineType = vaccineType;
        this.date = date;
        this.time = time;
        this.location = location;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}