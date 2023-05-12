package com.example.bilabonnement.model;

import java.util.ArrayList;
import java.util.List;

public class DashboardDTO {

    public DashboardDTO(){}
    private int monthly_income;
    private int cars_in_repair;
    private int cars_rented;
    private int cars_available;
    private List<ContractTypeCountDTO> contractTypeCountDTOList = new ArrayList<>();


    public int getMonthly_income() {
        return monthly_income;
    }

    public void setMonthly_income(int monthly_income) {
        this.monthly_income = monthly_income;
    }

    public int getCars_in_repair() {
        return cars_in_repair;
    }

    public void setCars_in_repair(int cars_in_repair) {
        this.cars_in_repair = cars_in_repair;
    }

    public int getCars_rented() {
        return cars_rented;
    }

    public void setCars_rented(int cars_rented) {
        this.cars_rented = cars_rented;
    }

    public int getCars_available() {
        return cars_available;
    }

    public void setCars_available(int cars_available) {
        this.cars_available = cars_available;
    }

    public List<ContractTypeCountDTO> getContractCountDTOList() {
        return contractTypeCountDTOList;
    }

    public void setContractCountDTOList(List<ContractTypeCountDTO> contractTypeCountDTOList) {
        this.contractTypeCountDTOList = contractTypeCountDTOList;
    }
}
