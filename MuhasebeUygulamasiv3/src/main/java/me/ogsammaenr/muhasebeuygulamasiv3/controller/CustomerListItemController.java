package me.ogsammaenr.muhasebeuygulamasiv3.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import me.ogsammaenr.muhasebeuygulamasiv3.model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerListItemController implements Initializable {

    @FXML
    private Label lblFirmaAdi;

    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        lblFirmaAdi.setText(customer.getFirmaAdi());
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

