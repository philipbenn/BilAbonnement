package com.example.bilabonnement.model.carModel;

public class Car_Model_Max_Km_Plan {

    public Car_Model_Max_Km_Plan(){}

    private Integer car_model_max_km_plan_id;
    private Integer car_model_id;
    private Integer max_km;
    private double km_price_per_month;

    public Integer getCar_model_max_km_plan_id() {
        return car_model_max_km_plan_id;
    }

    public void setCar_model_max_km_plan_id(Integer car_model_max_km_plan_id) {
        this.car_model_max_km_plan_id = car_model_max_km_plan_id;
    }

    public Integer getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(Integer car_model_id) {
        this.car_model_id = car_model_id;
    }

    public Integer getMax_km() {
        return max_km;
    }

    public void setMax_km(Integer max_km) {
        this.max_km = max_km;
    }

    public double getKm_price_per_month() {
        return km_price_per_month;
    }

    public void setKm_price_per_month(double km_price_per_month) {
        this.km_price_per_month = km_price_per_month;
    }
}
