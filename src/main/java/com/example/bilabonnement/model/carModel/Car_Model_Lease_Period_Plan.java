package com.example.bilabonnement.model.carModel;

public class Car_Model_Lease_Period_Plan {

    public Car_Model_Lease_Period_Plan() {
    }

    private Integer car_model_lease_period_plan_id;
    private Integer car_model_id;

    private Integer nr_of_months;
    private String type;

    public Integer getNr_of_months() {
        return nr_of_months;
    }

    public void setNr_of_months(Integer nr_of_months) {
        this.nr_of_months = nr_of_months;
    }

    private double price_per_month;

    public Integer getCar_model_lease_period_plan_id() {
        return car_model_lease_period_plan_id;
    }

    public void setCar_model_lease_period_plan_id(Integer car_model_lease_period_plan_id) {
        this.car_model_lease_period_plan_id = car_model_lease_period_plan_id;
    }

    public Integer getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(Integer car_model_id) {
        this.car_model_id = car_model_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice_per_month() {
        return price_per_month;
    }

    public void setPrice_per_month(double price_per_month) {
        this.price_per_month = price_per_month;
    }
}