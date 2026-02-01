package me.ogsammaenr.muhasebeuygulamasiv3.manager;

import me.ogsammaenr.muhasebeuygulamasiv3.model.Customer;
import java.util.ArrayList;

public class CustomerManager {
    private static CustomerManager instance;
    private ArrayList<Customer> customers;

    private CustomerManager() {
        this.customers = new ArrayList<>();
    }

    public static CustomerManager getInstance() {
        if (instance == null) {
            instance = new CustomerManager();
        }
        return instance;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public void removeCustomerById(String customerId) {
        customers.removeIf(c -> c.getId().equals(customerId));
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(String customerId) {
        return customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    public Customer getCustomerByFirmaAdi(String firmaAdi) {
        return customers.stream()
                .filter(c -> c.getFirmaAdi().equalsIgnoreCase(firmaAdi))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Customer> searchCustomers(String query) {
        ArrayList<Customer> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (Customer customer : customers) {
            if (customer.getFirmaAdi().toLowerCase().contains(lowerQuery) ||
                customer.getEposta().toLowerCase().contains(lowerQuery) ||
                customer.getTelefon().contains(query)) {
                results.add(customer);
            }
        }
        return results;
    }

    public int getTotalCustomerCount() {
        return customers.size();
    }
}

