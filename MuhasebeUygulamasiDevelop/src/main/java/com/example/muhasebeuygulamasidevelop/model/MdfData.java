package com.example.muhasebeuygulamasidevelop.model;

public class MdfData {
    private int thickness;
    private double width;
    private double height;

    private double patternTime;
    private double drillTime;
    private double unitPrice;

    private int count;

    public MdfData(int thickness, double width, double height, double patternTime, double drillTime, double unitPrice,  int count) {
        this.thickness = thickness;
        this.width = width;
        this.height = height;
        this.patternTime = patternTime;
        this.drillTime = drillTime;
        this.unitPrice = unitPrice;
        this.count = count;
    }

    public double getDrillTime() {
        return drillTime;
    }

    public double getHeight() {
        return height;
    }

    public double getPatternTime() {
        return patternTime;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getWidth() {
        return width;
    }

    public int getThickness() {
        return thickness;
    }

    /**
     * @return mm^2
     */
    public double getArea(){
        return width*height;
    }

    /**
     * @return mm^2
     */
    public double getTotalArea(){
        return width*height*count;
    }

    public double getTotalTime(){
        return patternTime+drillTime;
    }

    public double getTotalPrice(){
        return unitPrice*(patternTime+drillTime);
    }

    public int getCount() {
        return count;
    }
}
