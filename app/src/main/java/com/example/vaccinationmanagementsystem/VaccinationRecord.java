package com.example.vaccinationmanagementsystem;

public class VaccinationRecord {
    private String vaccineType;
    private String vaccinationDate;
    private String location;
    private String dose;
    private String doctor;
    private String batchNumber;
    private String certificateId;

    // Constructor đầy đủ
    public VaccinationRecord(String vaccineType, String vaccinationDate, String location, 
                           String dose, String doctor, String batchNumber, String certificateId) {
        this.vaccineType = vaccineType;
        this.vaccinationDate = vaccinationDate;
        this.location = location;
        this.dose = dose;
        this.doctor = doctor;
        this.batchNumber = batchNumber;
        this.certificateId = certificateId;
    }

    // Constructor đơn giản
    public VaccinationRecord(String vaccineType, String vaccinationDate, String location, 
                           String dose, String doctor) {
        this.vaccineType = vaccineType;
        this.vaccinationDate = vaccinationDate;
        this.location = location;
        this.dose = dose;
        this.doctor = doctor;
        this.batchNumber = "";
        this.certificateId = "";
    }

    // Getters and Setters
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

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    @Override
    public String toString() {
        return "VaccinationRecord{" +
                "vaccineType='" + vaccineType + '\'' +
                ", vaccinationDate='" + vaccinationDate + '\'' +
                ", location='" + location + '\'' +
                ", dose='" + dose + '\'' +
                ", doctor='" + doctor + '\'' +
                '}';
    }
}