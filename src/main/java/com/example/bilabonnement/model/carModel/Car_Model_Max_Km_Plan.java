package com.example.bilabonnement.model.carModel;

public class Car_Model_Max_Km_Plan {

    public Car_Model_Max_Km_Plan(){}

    private int car_model_max_km_plan_id;
    private int car_model_id;
    private int max_km;
    private double km_price_per_month;

    public int getCar_model_max_km_plan_id() {
        return car_model_max_km_plan_id;
    }

    public void setCar_model_max_km_plan_id(int car_model_max_km_plan_id) {
        this.car_model_max_km_plan_id = car_model_max_km_plan_id;
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

    public double getKm_price_per_month() {
        return km_price_per_month;
    }

    public void setKm_price_per_month(double km_price_per_month) {
        this.km_price_per_month = km_price_per_month;
    }
}
