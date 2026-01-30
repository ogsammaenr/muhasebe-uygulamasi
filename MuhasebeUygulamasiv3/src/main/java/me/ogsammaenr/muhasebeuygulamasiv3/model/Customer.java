package me.ogsammaenr.muhasebeuygulamasiv3.model;

public class Customer {
    private String id;
    private String firmaAdi;
    private String eposta;
    private String telefon;
    private String notlar;

    public Customer(String id, String firmaAdi, String eposta, String telefon, String notlar) {
        this.id = id;
        this.firmaAdi = firmaAdi;
        this.eposta = eposta;
        this.telefon = telefon;
        this.notlar = notlar;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirmaAdi() {
        return firmaAdi;
    }

    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNotlar() {
        return notlar;
    }

    public void setNotlar(String notlar) {
        this.notlar = notlar;
    }

    @Override
    public String toString() {
        return firmaAdi;
    }
}

