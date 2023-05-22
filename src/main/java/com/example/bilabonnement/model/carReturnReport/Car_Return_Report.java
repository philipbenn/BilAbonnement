package com.example.bilabonnement.model.carReturnReport;


public class Car_Return_Report {


    private Integer car_return_report_id;
    private Integer car_id;
    private Integer km_driven;
    private Integer contract_id;

    public Car_Return_Report() {
    }

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public Integer getCar_return_report_id() {
        return car_return_report_id;
    }

    public void setCar_return_report_id(Integer car_return_report_id) {
        this.car_return_report_id = car_return_report_id;
    }

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public Integer getKm_driven() {
        return km_driven;
    }

    public void setKm_driven(Integer km_driven) {
        this.km_driven = km_driven;
    }
}
