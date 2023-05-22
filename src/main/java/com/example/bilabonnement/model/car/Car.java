package com.example.bilabonnement.model.car;

public class Car {

    public Car(){}

    private Integer car_id;
    private Integer car_model_id;
    private String vognnummer;

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public Integer getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(Integer car_model_id) {
        this.car_model_id = car_model_id;
    }

    public String getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(String vognnummer) {
        this.vognnummer = vognnummer;
    }
}
