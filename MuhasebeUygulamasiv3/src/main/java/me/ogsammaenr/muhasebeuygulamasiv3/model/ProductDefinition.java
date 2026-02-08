package me.ogsammaenr.muhasebeuygulamasiv3.model;

public class ProductDefinition {
    public static String USD = "USD";
    public static String TL = "TL";

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
            this.currency = "TL"; // Varsayılan olarak TL
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
        private double multiplier;  // Maliyet hesaplamasında kullanılacak çarpan (örneğin, atık oranı)
        private String currency;    // "TL", "USD"

        public MaterialDefinition(String name, double price, UnitType unit) {
            super(name, price, unit);
            this.multiplier = 1.0;
            this.currency = "TL"; // Varsayılan olarak TL
        }

        public MaterialDefinition(String name, double price, UnitType unit, String currency) {
            super(name, price, unit);
            this.multiplier = 1.0;
            this.currency = currency;
        }

        public void setMultiplier(double multiplier) {
            this.multiplier = multiplier;
        }
        public double getMultiplier() {
            return multiplier;
        }
    }

    // İşçilikler
    public static class LaborDefinition extends CostDefinition {
        private String currency;  // "TL", "USD"

        public LaborDefinition(String name, double price, UnitType unit) {
            super(name, price, unit);
            this.currency = "TL"; // Varsayılan olarak TL
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }

    public enum UnitType {
        M2,
        DAKIKA,
        PLAKA,
        ADET,
        KG
    }
}
