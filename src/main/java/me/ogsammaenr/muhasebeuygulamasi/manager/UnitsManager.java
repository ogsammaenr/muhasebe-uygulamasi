package me.ogsammaenr.muhasebeuygulamasi.manager;

import me.ogsammaenr.muhasebeuygulamasi.model.Unit;

import java.util.HashMap;
import java.util.Map;

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
            totalTime += unit.getTotalTime();
        }
        return totalTime;
    }

    public double getTotalArea() {
        double totalArea = 0;
        for (Unit unit : unitMap.values()) {
            totalArea += unit.getArea();
        }
        return totalArea;
    }

    public Map<String, Unit> getUnitMap() {
        return unitMap;
    }
}
