package me.ogsammaenr.muhasebeuygulamasiv3.manager;

import me.ogsammaenr.muhasebeuygulamasiv3.model.Customer;

import java.awt.event.WindowStateListener;
import java.util.ArrayList;

public class CustomerManager {
    private ArrayList<Customer> customers;
    private static CustomerManager instance;

    private CustomerManager() {
        customers = new ArrayList<>();

        // Sample data
        customers.add(new Customer("1", "Firma A", "aasdf@gmail.com", "1234567890", "Notlar A"));
        customers.add(new Customer("2", "Firma B", "qwer@gmail.com", "0987654321", "Notlar B"));
        customers.add(new Customer("3", "Firma C", "ouabsdofas@gmail.com", "1122334455", "Notlar C"));
        customers.add(new Customer("4", "ABC Şirketler A.Ş.", "contact@abc.com", "+90 555 123 4567", "İstanbul'da bulunan üretim şirketi"));
        customers.add(new Customer("5", "XYZ Ticaret Ltd.", "info@xyz.com", "+90 555 234 5678", "İhracat ve ithalatla uğraşan firma"));
        customers.add(new Customer("6", "Tekno İnova Teknolojileri", "sales@tekno.com", "+90 555 345 6789", "Yazılım geliştirme ve danışmanlık"));
        customers.add(new Customer("7", "Marmara Lojistik", "logistics@marmara.com", "+90 555 456 7890", "Ulaştırma ve depolama hizmetleri"));
        customers.add(new Customer("8", "Doğu Harita Mühendislik", "engineering@doguharita.com", "+90 555 567 8901", "İnşaat ve harita mühendisliği"));


    }
    public static CustomerManager getInstance() {
        if (instance == null) {
            instance = new CustomerManager();
        }
        return instance;
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public void updateCustomer(Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(updatedCustomer.getId())) {
                customers.set(i, updatedCustomer);
                return;
            }
        }
    }


}
