package me.ogsammaenr.muhasebeuygulamasi.model;

import me.ogsammaenr.muhasebeuygulamasi.util.Utils;

public class Unit {
    private String unitId;
    private int measurex;
    private int measurey;
    private int measurez;
    private int count;
    private double patternTime;
    private double drillTime;
    private double totalTime;
    private double area;

    public Unit(String unitId, int measurex, int measurey, int measurez, int count, double patternTime, double drillTime) {
        this.unitId = unitId;
        this.measurex = measurex;
        this.measurey = measurey;
        this.measurez = measurez;
        this.count = count;
        this.patternTime = patternTime;
        this.drillTime = drillTime;
        this.totalTime = Utils.round((patternTime + drillTime) * count, 4);
        this.area = Utils.round((double) (measurex * measurey * count) / 1000000, 4);
    }

    public double getArea() {
        return area;
    }

    public double getDrillTime() {
        return drillTime;
    }

    public int getMeasurex() {
        return measurex;
    }

    public int getMeasurey() {
        return measurey;
    }

    public int getMeasurez() {
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
