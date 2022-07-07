package com.example.test.bl;



public class Training {
    private String name;
    private int duration;
    private double rate;

    public Training(String name, int duration, double rate) {
        this.name = name;
        this.duration = duration;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
