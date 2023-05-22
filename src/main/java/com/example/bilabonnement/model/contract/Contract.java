package com.example.bilabonnement.model.contract;

public class Contract {

    public Contract(){}

    private Integer contract_id;
    private Integer customer_id;
    private Integer employee_id;
    private Integer car_id;
    private Integer car_model_lease_period_plan_id;
    private Integer car_model_max_km_plan_id;
    private String start_date;
    private String end_date;

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public Integer getCar_model_lease_period_plan_id() {
        return car_model_lease_period_plan_id;
    }

    public void setCar_model_lease_period_plan_id(Integer car_model_lease_period_plan_id) {
        this.car_model_lease_period_plan_id = car_model_lease_period_plan_id;
    }

    public Integer getCar_model_max_km_plan_id() {
        return car_model_max_km_plan_id;
    }

    public void setCar_model_max_km_plan_id(Integer car_model_max_km_plan_id) {
        this.car_model_max_km_plan_id = car_model_max_km_plan_id;
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
