package com.example.vaccinationmanagementsystem.model;

public class Appointment {
    private int appointmentId;
    private int userId;
    private int vaccineId;
    private String appointmentDate;
    private String appointmentTime;
    private String status;
    private String location;
    private String createdAt;
    
    // Additional fields for display
    private String userName;
    private String vaccineName;

    // Constructor đầy đủ
    public Appointment(int appointmentId, int userId, int vaccineId, String appointmentDate, 
                      String appointmentTime, String status, String location, String createdAt) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.vaccineId = vaccineId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.location = location;
        this.createdAt = createdAt;
    }

    // Constructor để tạo appointment mới
    public Appointment(int userId, int vaccineId, String appointmentDate, 
                      String appointmentTime, String location) {
        this.userId = userId;
        this.vaccineId = vaccineId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.location = location;
        this.status = "scheduled";
    }

    // Constructor đơn giản
    public Appointment() {
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", userId=" + userId +
                ", vaccineId=" + vaccineId +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}