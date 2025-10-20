package com.example.vaccinationmanagementsystem.model;

public class Vaccine {
    private int vaccineId;
    private String vaccineName;
    private String vaccineType;
    private String manufacturer;
    private String description;
    private int dosesRequired;
    private int intervalDays;

    // Constructor đầy đủ
    public Vaccine(int vaccineId, String vaccineName, String vaccineType, String manufacturer, 
                  String description, int dosesRequired, int intervalDays) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.vaccineType = vaccineType;
        this.manufacturer = manufacturer;
        this.description = description;
        this.dosesRequired = dosesRequired;
        this.intervalDays = intervalDays;
    }

    // Constructor để tạo vaccine mới
    public Vaccine(String vaccineName, String vaccineType, String manufacturer, 
                  String description, int dosesRequired, int intervalDays) {
        this.vaccineName = vaccineName;
        this.vaccineType = vaccineType;
        this.manufacturer = manufacturer;
        this.description = description;
        this.dosesRequired = dosesRequired;
        this.intervalDays = intervalDays;
    }

    // Constructor đơn giản
    public Vaccine() {
    }

    // Getters and Setters
    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDosesRequired() {
        return dosesRequired;
    }

    public void setDosesRequired(int dosesRequired) {
        this.dosesRequired = dosesRequired;
    }

    public int getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(int intervalDays) {
        this.intervalDays = intervalDays;
    }

    @Override
    public String toString() {
        return vaccineName; // For spinner display
    }
}