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
            totalTime += unit.getTotalTime();
        }
        return Utils.round(totalTime, 2);
    }

    public double getTotalArea() {
        double totalArea = 0;
        for (Unit unit : unitMap.values()) {
            totalArea += unit.getArea();
        }
        return Utils.round(totalArea, 2);
    }

    public List<Integer> getAllThickness() {
        Set<Integer> thicknessSet = new LinkedHashSet<>();
        for (Unit unit : unitMap.values()) {
            thicknessSet.add(unit.getMeasurez());
        }
        return new ArrayList<>(thicknessSet);
    }

    public double thicnessToArea(double thickness) {
        double area = 0;

        for (Unit unit : unitMap.values()) {
            if (thickness == unit.getMeasurez()) {
                area += unit.getArea();
            }
        }

        return area;
    }

    public Map<String, Unit> getUnitMap() {
        return unitMap;
    }
}
