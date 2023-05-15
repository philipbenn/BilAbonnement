package com.example.bilabonnement.model;

public class Car_Return_DamageDTO {

    public Car_Return_DamageDTO(){}

    private String vognnummer;
    private String car_model;
    private int car_return_damage_id;

    private int car_return_report_id;

    private String damage_description;

    private boolean isFixed;

    private double price;

    public String getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(String vognnummer) {
        this.vognnummer = vognnummer;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public int getCar_return_damage_id() {
        return car_return_damage_id;
    }

    public void setCar_return_damage_id(int car_return_damage_id) {
        this.car_return_damage_id = car_return_damage_id;
    }

    public int getCar_return_report_id() {
        return car_return_report_id;
    }

    public void setCar_return_report_id(int car_return_report_id) {
        this.car_return_report_id = car_return_report_id;
    }

    public String getDamage_description() {
        return damage_description;
    }

    public void setDamage_description(String damage_description) {
        this.damage_description = damage_description;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
