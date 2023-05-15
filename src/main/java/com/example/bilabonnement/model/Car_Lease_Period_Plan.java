package com.example.bilabonnement.model;

public class Car_Lease_Period_Plan {

    public Car_Lease_Period_Plan() {
    }

    private int car_lease_period_plan_id;
    private int car_model_id;

    private int nr_of_months;
    private String type;

    public int getNr_of_months() {
        return nr_of_months;
    }

    public void setNr_of_months(int nr_of_months) {
        this.nr_of_months = nr_of_months;
    }

    private double price_per_month;

    public int getCar_lease_period_plan_id() {
        return car_lease_period_plan_id;
    }

    public void setCar_lease_period_plan_id(int car_lease_period_plan_id) {
        this.car_lease_period_plan_id = car_lease_period_plan_id;
    }

    public int getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(int car_model_id) {
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