package com.example.bilabonnement.model.carReturnReport;

public class Car_Return_Report_DTO {

    private Integer car_return_report_id;
    private Integer contract_id;
    private String vognnummer;
    private String car_model_name;
    private Integer nrOfDamages;
    Car_Return_Report_DTO(){}

    public int getCar_return_report_id() {
        return car_return_report_id;
    }

    public void setCar_return_report_id(int car_return_report_id) {
        this.car_return_report_id = car_return_report_id;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public String getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(String vognnummer) {
        this.vognnummer = vognnummer;
    }

    public String getCar_model_name() {
        return car_model_name;
    }

    public void setCar_model_name(String car_model_name) {
        this.car_model_name = car_model_name;
    }

    public int getNrOfDamages() {
        return nrOfDamages;
    }

    public void setNrOfDamages(int nrOfDamages) {
        this.nrOfDamages = nrOfDamages;
    }
}
