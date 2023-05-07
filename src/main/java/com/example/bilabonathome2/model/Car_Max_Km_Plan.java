package com.example.bilabonathome2.model;

public class Car_Max_Km_Plan {

    public Car_Max_Km_Plan(){}

    private int car_max_km_plan_id;
    private int car_model_id;
    private int max_km;
    private double price_per_month;

    public int getCar_max_km_plan_id() {
        return car_max_km_plan_id;
    }

    public void setCar_max_km_plan_id(int car_max_km_plan_id) {
        this.car_max_km_plan_id = car_max_km_plan_id;
    }

    public int getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(int car_model_id) {
        this.car_model_id = car_model_id;
    }

    public int getMax_km() {
        return max_km;
    }

    public void setMax_km(int max_km) {
        this.max_km = max_km;
    }

    public double getPrice_per_month() {
        return price_per_month;
    }

    public void setPrice_per_month(double price_per_month) {
        this.price_per_month = price_per_month;
    }
}
