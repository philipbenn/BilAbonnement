package com.example.bilabonnement.model.carReturnReport;

public class Car_Return_Damage {

    private Integer car_return_damage_id;
    private Integer car_return_report_id;
    private String damage_description;
    private Integer isFixed;
    private double price;

    public Car_Return_Damage() {
    }

    public Integer getCar_return_damage_id() {
        return car_return_damage_id;
    }

    public void setCar_return_damage_id(Integer car_return_damage_id) {
        this.car_return_damage_id = car_return_damage_id;
    }

    public Integer getCar_return_report_id() {
        return car_return_report_id;
    }

    public void setCar_return_report_id(Integer car_return_report_id) {
        this.car_return_report_id = car_return_report_id;
    }

    public String getDamage_description() {
        return damage_description;
    }

    public void setDamage_description(String damage_description) {
        this.damage_description = damage_description;
    }

    public Integer getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Integer isFixed) {
        this.isFixed = isFixed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}