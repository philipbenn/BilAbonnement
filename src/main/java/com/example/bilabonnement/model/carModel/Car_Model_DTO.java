package com.example.bilabonnement.model.carModel;

import java.util.List;

public class Car_Model_DTO {

    public Car_Model_DTO() {
    }
    private Car_Model car_model;
    private List<Car_Model_Lease_Period_Plan> car_model_lease_period_plans;
    private List<Car_Model_Max_Km_Plan> car_model_max_km_plans;
    private Integer carsLeasedOut;
    private Integer carsInStock;
    private Integer getCarsLeasedOut;
    private Integer carsInRepair;
    public Integer getCarsLeasedOut() {
        return carsLeasedOut;
    }

    public Car_Model getCar_model() {
        return car_model;
    }

    public void setCar_model(Car_Model car_model) {
        this.car_model = car_model;
    }

    public List<Car_Model_Lease_Period_Plan> getCar_model_lease_period_plans() {
        return car_model_lease_period_plans;
    }

    public void setCar_model_lease_period_plans(List<Car_Model_Lease_Period_Plan> car_model_lease_period_plans) {
        this.car_model_lease_period_plans = car_model_lease_period_plans;
    }

    public List<Car_Model_Max_Km_Plan> getCar_model_max_km_plans() {
        return car_model_max_km_plans;
    }

    public void setCar_model_max_km_plans(List<Car_Model_Max_Km_Plan> car_model_max_km_plans) {
        this.car_model_max_km_plans = car_model_max_km_plans;
    }

    public void setCarsLeasedOut(Integer carsLeasedOut) {
        this.carsLeasedOut = carsLeasedOut;
    }

    public Integer getCarsInStock() {
        return carsInStock;
    }

    public void setCarsInStock(Integer carsInStock) {
        this.carsInStock = carsInStock;
    }

    public Integer getGetCarsLeasedOut() {
        return getCarsLeasedOut;
    }

    public void setGetCarsLeasedOut(Integer getCarsLeasedOut) {
        this.getCarsLeasedOut = getCarsLeasedOut;
    }

    public Integer getCarsInRepair() {
        return carsInRepair;
    }

    public void setCarsInRepair(Integer carsInRepair) {
        this.carsInRepair = carsInRepair;
    }
}
