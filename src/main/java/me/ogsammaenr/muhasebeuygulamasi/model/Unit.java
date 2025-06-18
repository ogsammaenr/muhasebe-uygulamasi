package me.ogsammaenr.muhasebeuygulamasi.model;

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
        this.measurex = measurex;
        this.measurey = measurey;
        this.measurez = measurez;
        this.count = count;
        this.patternTime = patternTime;
        this.drillTime = drillTime;
        this.totalTime = (patternTime + drillTime) * count;
        this.area = (measurex * measurey * count) / 1000000;
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
