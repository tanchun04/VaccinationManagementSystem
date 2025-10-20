package com.example.vaccinationmanagementsystem;

public class Vaccine {
    private String name;
    private String manufacturer;
    private String lotNumber;
    private int quantity;
    private String expiryDate;

    public Vaccine(String name, String manufacturer, String lotNumber, int quantity, String expiryDate) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.lotNumber = lotNumber;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}