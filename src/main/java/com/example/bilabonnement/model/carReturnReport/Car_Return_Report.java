package com.example.bilabonnement.model.carReturnReport;


public class Car_Return_Report {


    private int car_return_report_id;
    private int car_id;
    private int km_driven;
    private int contract_id;

    public Car_Return_Report() {
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getCar_return_report_id() {
        return car_return_report_id;
    }

    public void setCar_return_report_id(int car_return_report_id) {
        this.car_return_report_id = car_return_report_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getKm_driven() {
        return km_driven;
    }

    public void setKm_driven(int km_driven) {
        this.km_driven = km_driven;
    }
}
