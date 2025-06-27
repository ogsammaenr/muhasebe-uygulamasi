package me.ogsammaenr.muhasebeuygulamasi.model;

import java.time.LocalDate;

public class Client {
    private int id;
    private String companyName;
    private int productCount;
    private LocalDate registrationDate;
    private LocalDate lastActionDate;
    private String eMailAddress;
    private String phoneNumber;
    private String notes;

    /**
     * For new Clients
     *
     * @param id
     * @param companyName
     * @param productCount
     */
    public Client(int id, String companyName, int productCount) {
        this.id = id;
        this.companyName = companyName;
        this.productCount = productCount;
        this.registrationDate = LocalDate.now();
    }

    /**
     * For load Clients
     *
     * @param id
     * @param companyName
     * @param productCount
     * @param registrationDate
     * @param lastActionDate
     */
    public Client(int id, String companyName, int productCount, LocalDate registrationDate, LocalDate lastActionDate) {
        this.id = id;
        this.companyName = companyName;
        this.productCount = productCount;
        this.registrationDate = registrationDate;
        this.lastActionDate = lastActionDate;
    }

    /**
     * add 1 to productCount
     */
    public void addProduct() {
        productCount++;
    }

    public void setLastActionDate() {
        this.lastActionDate = LocalDate.now();
    }

    public void setEMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /*      Getters     */
    public int getId() {
        return id;
    }

    public int getProductCount() {
        return productCount;
    }

    public LocalDate getLastActionDate() {
        return lastActionDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
