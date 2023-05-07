package com.example.bilabonathome2.model;

import java.time.LocalDate;

public class ContractInfo {

    public ContractInfo(){}
    private int contractId;
    private int customerId;
    private String customerName;
    private int carId;
    private int carModelId;
    private String carModel;
    private String vognnummer;
    private int leasePeriodPlanId;
    private String leaseType;
    private double leasePrice;
    private int maxKmPlanId;
    private int maxKm;
    private double kmPlanPrice;
    private String startDate;
    private String endDate;
    private double total_price_per_month;

    public double getTotal_price_per_month() {
        return total_price_per_month;
    }

    public void setTotal_price_per_month(double total_price_per_month) {
        this.total_price_per_month = total_price_per_month;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(String vognnummer) {
        this.vognnummer = vognnummer;
    }

    public int getLeasePeriodPlanId() {
        return leasePeriodPlanId;
    }

    public void setLeasePeriodPlanId(int leasePeriodPlanId) {
        this.leasePeriodPlanId = leasePeriodPlanId;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public double getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(double leasePrice) {
        this.leasePrice = leasePrice;
    }

    public int getMaxKmPlanId() {
        return maxKmPlanId;
    }

    public void setMaxKmPlanId(int maxKmPlanId) {
        this.maxKmPlanId = maxKmPlanId;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public double getKmPlanPrice() {
        return kmPlanPrice;
    }

    public void setKmPlanPrice(double kmPlanPrice) {
        this.kmPlanPrice = kmPlanPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


// Getters and setters here
}
