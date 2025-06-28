package me.ogsammaenr.muhasebeuygulamasi.model;

import java.time.LocalDate;

public class Client {
    private int id;
    private String companyName;
    private LocalDate registrationDate;
    private LocalDate lastActionDate;
    private String eMailAddress;
    private String phoneNumber;
    private String notes;

    /**
     * For new Clients
     *
     * @param companyName
     */
    public Client(String companyName) {
        this.companyName = companyName;
        this.registrationDate = LocalDate.now();
    }

    /**
     * For load Clients
     *
     * @param id
     * @param companyName
     * @param registrationDate
     * @param lastActionDate
     */
    public Client(int id, String companyName, LocalDate registrationDate, LocalDate lastActionDate) {
        this.id = id;
        this.companyName = companyName;
        this.registrationDate = registrationDate;
        this.lastActionDate = lastActionDate;
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

    public void setId(int id) {
        this.id = id;
    }

    /*      Getters     */
    public int getId() {
        return id;
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

    public String getEMailAddress() {
        return eMailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNotes() {
        return notes;
    }
}
