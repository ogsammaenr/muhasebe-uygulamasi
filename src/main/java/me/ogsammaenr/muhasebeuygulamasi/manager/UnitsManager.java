package me.ogsammaenr.muhasebeuygulamasi.manager;

import me.ogsammaenr.muhasebeuygulamasi.model.Unit;
import me.ogsammaenr.muhasebeuygulamasi.util.Utils;

import java.util.*;

public class UnitsManager {

    private final Map<String, Unit> unitMap;

    public UnitsManager() {
        this.unitMap = new HashMap<>();
    }

    public void addUnit(Unit unit) {
        unitMap.put(unit.getUnitId(), unit);
    }

    public Unit getUnitById(String unitId) {
        return unitMap.get(unitId);
    }

    public double getUnitTime(String unitId) {
        return unitMap.get(unitId).getTotalTime();
    }

    public double getUnitArea(String unitId) {
        return unitMap.get(unitId).getArea();
    }

    public double getTotalTime() {
        double totalTime = 0;
        for (Unit unit : unitMap.values()) {
            totalTime += Utils.round(unit.getTotalTime(), 2);
        }
        return totalTime;
    }

    public double getTotalArea() {
        double totalArea = 0;
        for (Unit unit : unitMap.values()) {
            totalArea += Utils.round(unit.getArea(), 2);
        }
        return totalArea;
    }

    public List<Double> getAllThickness() {
        Set<Double> thicknessSet = new LinkedHashSet<>();
        for (Unit unit : unitMap.values()) {
            thicknessSet.add(Utils.round(unit.getMeasurez(), 2));
        }
        return new ArrayList<>(thicknessSet);
    }

    public double thicnessToArea(double thickness) {
        double area = 0;

        for (Unit unit : unitMap.values()) {
            if (thickness == Utils.round(unit.getMeasurez(), 2)) {
                area += Utils.round(unit.getArea(), 2);
            }
        }

        return area;
    }

    public Map<String, Unit> getUnitMap() {
        return unitMap;
    }
}
