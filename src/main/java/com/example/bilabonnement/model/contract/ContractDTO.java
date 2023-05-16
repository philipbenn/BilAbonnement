package com.example.bilabonnement.model.contract;

public class ContractDTO {

    public ContractDTO(){}
    private int contract_id;
    private int customer_id;
    private String customer_name;
    private int car_id;
    private int car_model_id;
    private String car_model;
    private String vognnummer;
    private int car_model_lease_period_plan_id;
    private String lease_type;
    private double lease_price;
    private int car_model_max_km_plan_id;
    private int max_km;
    private double km_plan_price;
    private String start_date;
    private String end_date;
    private double total_price_per_month;

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

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

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(String vognnummer) {
        this.vognnummer = vognnummer;
    }

    public int getCar_model_lease_period_plan_id() {
        return car_model_lease_period_plan_id;
    }

    public void setCar_model_lease_period_plan_id(int car_model_lease_period_plan_id) {
        this.car_model_lease_period_plan_id = car_model_lease_period_plan_id;
    }

    public String getLease_type() {
        return lease_type;
    }

    public void setLease_type(String lease_type) {
        this.lease_type = lease_type;
    }

    public double getLease_price() {
        return lease_price;
    }

    public void setLease_price(double lease_price) {
        this.lease_price = lease_price;
    }

    public int getCar_model_max_km_plan_id() {
        return car_model_max_km_plan_id;
    }

    public void setCar_model_max_km_plan_id(int car_model_max_km_plan_id) {
        this.car_model_max_km_plan_id = car_model_max_km_plan_id;
    }

    public int getMax_km() {
        return max_km;
    }

    public void setMax_km(int max_km) {
        this.max_km = max_km;
    }

    public double getKm_plan_price() {
        return km_plan_price;
    }

    public void setKm_plan_price(double km_plan_price) {
        this.km_plan_price = km_plan_price;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public double getTotal_price_per_month() {
        return total_price_per_month;
    }

    public void setTotal_price_per_month(double total_price_per_month) {
        this.total_price_per_month = total_price_per_month;
    }
}
