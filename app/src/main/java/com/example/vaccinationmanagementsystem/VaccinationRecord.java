package com.example.vaccinationmanagementsystem;

public class VaccinationRecord {
    private String vaccineName;
    private String vaccineType;
    private String vaccinationDate;
    private String location;
    private String lotNumber;
    private String status;

    public VaccinationRecord(String vaccineName, String vaccineType, String vaccinationDate, 
                           String location, String lotNumber, String status) {
        this.vaccineName = vaccineName;
        this.vaccineType = vaccineType;
        this.vaccinationDate = vaccinationDate;
        this.location = location;
        this.lotNumber = lotNumber;
        this.status = status;
    }

    // Getters and Setters
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

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}