package me.ogsammaenr.muhasebeuygulamasiv3.model;

public class ProductDefinition {


    // ATA SINIF
    public static abstract class CostDefinition {
        private String id;
        private String name;
        private double basePrice; // Birim fiyat
        private UnitType unitType;
        private String currency; // "TL", "USD"

        public CostDefinition(String name, double price, UnitType unit) {
            this.name = name;
            this.basePrice = price;
            this.unitType = unit;
        }

        public String getId() {
            return id;
        }

        public double getBasePrice() {
            return basePrice;
        }
        public UnitType getUnitType() {
            return unitType;
        }

        public String getCurrency() {
            return currency;
        }

        public String getName() {
            return name;
        }
    }

    // Malzemeler
    public static class MaterialDefinition extends CostDefinition {
        private double wasteRate;
        private String currency;

        public MaterialDefinition(String name, double price, UnitType unit) {
            super(name, price, unit);
            this.wasteRate = 0.0;
            this.currency = "TL"; // Varsayılan olarak TL
        }

        public MaterialDefinition(String name, double price, UnitType unit, String currency) {
            super(name, price, unit);
            this.wasteRate = 0.0;
            this.currency = currency;
        }

        public void setWasteRate(double wasteRate) {
            this.wasteRate = wasteRate;
        }

        public double getWasteRate() {
            return wasteRate;
        }
    }

    // İşçilikler
    public static class LaborDefinition extends CostDefinition {
        private String currency;
        public LaborDefinition(String name, double price, UnitType unit) {
            super(name, price, unit);
            this.currency = "TL"; // Varsayılan olarak TL
        }

        public LaborDefinition(String name, double price, UnitType unit, String currency) {
            super(name, price, unit);
            this.currency = currency;
        }
    }

    public enum UnitType {
        M2,
        ADET,
        DAKIKA,
        PLAKA,
        KG
    }
}
