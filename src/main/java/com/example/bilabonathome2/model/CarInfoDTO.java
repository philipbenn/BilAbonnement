package com.example.bilabonathome2.model;

import java.util.List;

public class CarInfoDTO {

    public CarInfoDTO() {
    }

    private Car_Model car_model;
    private List<Car_Lease_Period_Plan> car_lease_period_plans;
    private List<Car_Max_Km_Plan> car_max_km_plans;

    private int carsLeasedOut;
    private int carsInStock;
    private int getCarsLeasedOut;
    private int carsInRepair;

    public int getCarsLeasedOut() {
        return carsLeasedOut;
    }

    public void setCarsLeasedOut(int carsLeasedOut) {
        this.carsLeasedOut = carsLeasedOut;
    }

    public int getCarsInStock() {
        return carsInStock;
    }

    public void setCarsInStock(int carsInStock) {
        this.carsInStock = carsInStock;
    }

    public int getGetCarsLeasedOut() {
        return getCarsLeasedOut;
    }

    public void setGetCarsLeasedOut(int getCarsLeasedOut) {
        this.getCarsLeasedOut = getCarsLeasedOut;
    }

    public int getCarsInRepair() {
        return carsInRepair;
    }

    public void setCarsInRepair(int carsInRepair) {
        this.carsInRepair = carsInRepair;
    }

    public List<Car_Lease_Period_Plan> getCar_lease_period_plans() {
        return car_lease_period_plans;
    }

    public void setCar_lease_period_plans(List<Car_Lease_Period_Plan> car_lease_period_plans) {
        this.car_lease_period_plans = car_lease_period_plans;
    }

    public List<Car_Max_Km_Plan> getCar_max_km_plans() {
        return car_max_km_plans;
    }

    public void setCar_max_km_plans(List<Car_Max_Km_Plan> car_max_km_plans) {
        this.car_max_km_plans = car_max_km_plans;
    }

    public Car_Model getCar_model() {
        return car_model;
    }

    public void setCar_model(Car_Model car_model) {
        this.car_model = car_model;
    }
}
