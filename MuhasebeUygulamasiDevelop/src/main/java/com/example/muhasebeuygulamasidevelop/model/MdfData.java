package com.example.muhasebeuygulamasidevelop.model;

public class MdfData {
    private int thickness;
    private double width;
    private double height;

    private double patternTime;
    private double drillTime;
    private double unitPrice;

    public MdfData(int thickness, double width, double height, double patternTime, double drillTime, double unitPrice) {
        this.thickness = thickness;
        this.width = width;
        this.height = height;
        this.patternTime = patternTime;
        this.drillTime = drillTime;
        this.unitPrice = unitPrice;
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

    public double getArea(){
        return width*height;
    }

    public double getTotalTime(){
        return patternTime+drillTime;
    }

    public double getTotalPrice(){
        return unitPrice*(patternTime+drillTime);
    }


}
