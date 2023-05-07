package com.example.bilabonathome2.model;

public class Car_Return_Damage {

    public Car_Return_Damage(){}

    private int car_return_damage_id;

    private int car_return_report_id;

    private String damage_description;

    private Boolean isFixed;

    private double price;

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

    public Boolean getFixed() {
        return isFixed;
    }

    public void setFixed(Boolean fixed) {
        isFixed = fixed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
