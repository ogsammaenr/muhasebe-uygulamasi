package me.ogsammaenr.muhasebeuygulamasiv3.manager;

import me.ogsammaenr.muhasebeuygulamasiv3.model.ProductDefinition;

import java.util.ArrayList;

public class DefinitionManager {
    private static DefinitionManager instance;
    private ArrayList<ProductDefinition.MaterialDefinition> materials;
    private ArrayList<ProductDefinition.LaborDefinition> labors;

    private DefinitionManager() {
        this.materials = new ArrayList<>();
        this.labors = new ArrayList<>();
        initDefinitions();
    }

    // Singleton Pattern
    public static DefinitionManager getInstance() {
        if (instance == null) {
            instance = new DefinitionManager();
        }
        return instance;
    }

    private void initDefinitions() {
        // Malzemeler - TXT dosyasındaki verilerden
        addMaterial("MDF 08", 1550, ProductDefinition.UnitType.M2, "TL", 1.12 / 5.88);
        addMaterial("MDF 12", 1940, ProductDefinition.UnitType.M2, "TL", 1.12 / 5.88);
        addMaterial("MDF 18", 2480, ProductDefinition.UnitType.M2, "TL", 1.12 / 5.88);
        addMaterial("MDF 22", 2260, ProductDefinition.UnitType.M2, "TL", 1.12 / 5.88);
        addMaterial("MDF 25", 3775, ProductDefinition.UnitType.M2, "TL", 1.12 / 5.88);
        addMaterial("MDF 30", 4530, ProductDefinition.UnitType.M2, "TL", 1.12 / 5.88);
        addMaterial("PVC", 0, ProductDefinition.UnitType.M2, "USD", 2 * 1.03);  // PVC dolar bazlı
        addMaterial("Tutkal", 0, ProductDefinition.UnitType.M2, "TL");
        addMaterial("Zimpara", 0, ProductDefinition.UnitType.M2, "TL");
        addMaterial("Kesim", 250, ProductDefinition.UnitType.M2, "TL", 1 / 5.88);

        // İşçilikler
        addLabor("CNC (Dakika)", 27, ProductDefinition.UnitType.DAKIKA);
        addLabor("Zimpara İşçiliği", 60, ProductDefinition.UnitType.M2);
        addLabor("Paletleme İşçiliği", 25, ProductDefinition.UnitType.M2);
        addLabor("Tutkal İşçiliği", 70, ProductDefinition.UnitType.M2);
        addLabor("Basım İşçiliği", 350, ProductDefinition.UnitType.M2);
        addLabor("Ambalaj", 25, ProductDefinition.UnitType.M2);
        addLabor("Nakliye", 100, ProductDefinition.UnitType.ADET);
    }

    public void addMaterial(String name, double price, ProductDefinition.UnitType unit, String currency) {
        ProductDefinition.MaterialDefinition material = new ProductDefinition.MaterialDefinition(name, price, unit, currency);
        this.materials.add(material);
    }
    public void addMaterial(String name, double price, ProductDefinition.UnitType unit, String currency, double multiplier) {
        ProductDefinition.MaterialDefinition material = new ProductDefinition.MaterialDefinition(name, price, unit, currency);
        material.setMultiplier(multiplier);
        this.materials.add(material);
    }

    public void addLabor(String name, double price, ProductDefinition.UnitType unit) {
        ProductDefinition.LaborDefinition labor = new ProductDefinition.LaborDefinition(name, price, unit);
        this.labors.add(labor);
    }

    public void addLabor(String name, double price, ProductDefinition.UnitType unit, String currency) {
        ProductDefinition.LaborDefinition labor = new ProductDefinition.LaborDefinition(name, price, unit);
        labor.setCurrency(currency);
        this.labors.add(labor);
    }

    public ProductDefinition.CostDefinition getDefinitionById(String id) {
        ProductDefinition.CostDefinition material = materials.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (material != null) {
            return material;
        }

        return labors.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<ProductDefinition.MaterialDefinition> getMaterials() {
        return materials;
    }

    public ArrayList<ProductDefinition.LaborDefinition> getLabors() {
        return labors;
    }

    public ProductDefinition.MaterialDefinition getMaterialByName(String name) {
        return materials.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public ProductDefinition.LaborDefinition getLaborByName(String name) {
        return labors.stream()
                .filter(l -> l.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}

