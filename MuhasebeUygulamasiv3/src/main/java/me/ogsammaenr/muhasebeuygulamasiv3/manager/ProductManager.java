package me.ogsammaenr.muhasebeuygulamasiv3.manager;

import me.ogsammaenr.muhasebeuygulamasiv3.model.Product;
import java.util.ArrayList;

public class ProductManager {
    private static ProductManager instance;
    private ArrayList<Product> products;

    private ProductManager() {
        this.products = new ArrayList<>();
    }

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void removeProductById(String productId) {
        products.removeIf(p -> p.getId().equals(productId));
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(String productId) {
        return products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Product> getProductsByCustomer(String customerName) {
        ArrayList<Product> customerProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCustomerName().equalsIgnoreCase(customerName)) {
                customerProducts.add(product);
            }
        }
        return customerProducts;
    }

    public int getTotalProductCount() {
        return products.size();
    }
}

