package com.example.bilabonnement.model.contract;

public class Contract {

    public Contract(){}

    private int contract_id;
    private int customer_id;
    private int car_id;
    private int car_model_lease_period_plan_id;
    private int car_model_max_km_plan;
    private String start_date;
    private String end_date;

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getCar_model_lease_period_plan_id() {
        return car_model_lease_period_plan_id;
    }

    public void setCar_model_lease_period_plan_id(int car_model_lease_period_plan_id) {
        this.car_model_lease_period_plan_id = car_model_lease_period_plan_id;
    }

    public int getCar_model_max_km_plan() {
        return car_model_max_km_plan;
    }

    public void setCar_model_max_km_plan(int car_model_max_km_plan) {
        this.car_model_max_km_plan = car_model_max_km_plan;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
