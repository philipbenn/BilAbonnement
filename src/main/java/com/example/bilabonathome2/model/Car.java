package com.example.bilabonathome2.model;

public class Car {

    public Car(){}

    private int car_id;
    private int car_model_id;
    private String vognummer;

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(int car_model_id) {
        this.car_model_id = car_model_id;
    }

    public String getVognummer() {
        return vognummer;
    }

    public void setVognummer(String vognummer) {
        this.vognummer = vognummer;
    }
}
