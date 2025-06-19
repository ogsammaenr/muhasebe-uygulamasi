package me.ogsammaenr.muhasebeuygulamasi.model;

import me.ogsammaenr.muhasebeuygulamasi.util.Utils;

public class Unit {
    private String unitId;
    private double measurex;
    private double measurey;
    private double measurez;
    private int count;
    private double patternTime;
    private double drillTime;
    private double totalTime;
    private double area;

    public Unit(String unitId, double measurex, double measurey, double measurez, int count, double patternTime, double drillTime) {
        this.unitId = unitId;
        this.measurex = Utils.round(measurex, 2);
        this.measurey = Utils.round(measurey, 2);
        this.measurez = Utils.round(measurez, 2);
        this.count = count;
        this.patternTime = patternTime;
        this.drillTime = drillTime;
        this.totalTime = Utils.round((patternTime + drillTime) * count, 2);
        this.area = Utils.round((measurex * measurey * count) / 1000000, 2);
    }

    public double getArea() {
        return area;
    }

    public double getDrillTime() {
        return drillTime;
    }

    public double getMeasurex() {
        return measurex;
    }

    public double getMeasurey() {
        return measurey;
    }

    public double getMeasurez() {
        return measurez;
    }

    public double getPatternTime() {
        return patternTime;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public String getUnitId() {
        return unitId;
    }

    public int getCount() {
        return count;
    }
}
