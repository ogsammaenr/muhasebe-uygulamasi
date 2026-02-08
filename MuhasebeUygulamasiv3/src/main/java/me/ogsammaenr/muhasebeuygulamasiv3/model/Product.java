package me.ogsammaenr.muhasebeuygulamasiv3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Product {
    private String id;
    private String customerName;           // Firma Adı
    private String modelName;              // Model Adı
    private LocalDate createdDate;
    private double dollarRate;             // Ürün eklendiğindeki dolar kuru

    private ArrayList<CostItem> costItems; // Tüm maliyetler

    public Product(String customerName, String modelName, double dollarRate) {
        this.customerName = customerName;
        this.modelName = modelName;
        this.createdDate = LocalDate.now();
        this.dollarRate = dollarRate;
        this.id = UUID.randomUUID().toString();
        this.costItems = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public double getDollarRate() {
        return dollarRate;
    }

    public void setDollarRate(double dollarRate) {
        this.dollarRate = dollarRate;
    }

    // MALİYET YÖNETİMİ

    public void addCostItem(CostItem item) {
        costItems.add(item);
    }

    public void removeCostItem(CostItem item) {
        costItems.remove(item);
    }

    public void removeCostItem(int index) {
        if (index >= 0 && index < costItems.size()) {
            costItems.remove(index);
        }
    }

    public ArrayList<CostItem> getCostItems() {
        return costItems;
    }

    public CostItem getCostItem(int index) {
        if (index >= 0 && index < costItems.size()) {
            return costItems.get(index);
        }
        return null;
    }

    public double getTotalCost() {
        double total = 0;
        for (CostItem item : costItems) {
            total += item.calculateTotalCost(this.dollarRate);
        }
        return total;
    }

    public double getMaterialsCost() {
        double total = 0;
        for (CostItem item : costItems) {
            if (item.getDefinition() instanceof ProductDefinition.MaterialDefinition) {
                total += item.calculateTotalCost(this.dollarRate);
            }
        }
        return total;
    }

    public double getLaborCost() {
        double total = 0;
        for (CostItem item : costItems) {
            if (item.getDefinition() instanceof ProductDefinition.LaborDefinition) {
                total += item.calculateTotalCost(this.dollarRate);
            }
        }
        return total;
    }

    public int getCostItemCount() {
        return costItems.size();
    }


    public class CostItem {
        private ProductDefinition.CostDefinition definition;  // Referans
        private double quantity;                              // Miktar
        private String desc;                                  // İsteğe bağlı açıklama
        private boolean isCustomLabor;                        // Ek işçilik mi?

        public CostItem(ProductDefinition.CostDefinition definition, double quantity) {
            this.definition = definition;
            this.quantity = quantity;
            this.desc = "";
            this.isCustomLabor = false;
        }

        public CostItem(ProductDefinition.CostDefinition definition, double quantity, String desc) {
            this.definition = definition;
            this.quantity = quantity;
            this.desc = desc;
            this.isCustomLabor = false;
        }

        /**
         * ek işçilik için özel CostItem oluşturucu
         * @param laborName
         * @param quantity
         * @param customPrice
         * @param desc
         */
        public CostItem(String laborName, double quantity, double customPrice, String desc) {
            this.definition = new ProductDefinition.LaborDefinition(laborName, customPrice, ProductDefinition.UnitType.M2);
            this.quantity = quantity;
            this.desc = desc;
            this.isCustomLabor = true;
        }

        public ProductDefinition.CostDefinition getDefinition() {
            return definition;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getDescription() {
            return desc;
        }

        public void setDescription(String description) {
            this.desc = description;
        }

        public boolean isCustomLabor() {
            return isCustomLabor;
        }

        public double calculateTotalCost(double dollarRate) {
            double basePrice = this.definition.getBasePrice();

            if (this.definition.getCurrency().equals("USD")) {
                basePrice = basePrice * dollarRate;
            }

            return this.quantity * basePrice;
        }

        public String getItemSummary() {
            return String.format("%s | %.2f %s",
                definition.getName(),
                quantity,
                definition.getUnitType());
        }

        public String getDetailedSummary() {
            double cost = calculateTotalCost(Product.this.dollarRate);
            return String.format("%s | %.2f %s | %.2f TL%s",
                definition.getName(),
                quantity,
                definition.getUnitType(),
                cost,
                desc.isEmpty() ? "" : " (" + desc + ")");
        }
    }
}

