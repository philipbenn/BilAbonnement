package com.example.bilabonnement.service;

import com.example.bilabonnement.model.contract.Contract;
import com.example.bilabonnement.model.contract.Contract_DTO;
import com.example.bilabonnement.model.contract.Contract_Type_Count;
import com.example.bilabonnement.repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    ContractRepo contractRepo;

    // ContractDTO lists
    public List<Contract_DTO> expiringContracts(){

        return contractRepo.expiringContracts();
    }

    public List<Contract_DTO> getCustomerHistory(Integer customer_id){
        return contractRepo.getCustomerHistory(customer_id);
    }
    public List<Contract_DTO> getActiveContracts() {
        return contractRepo.getActiveContracts();
    }
    public List<Contract_DTO> editContract(Integer contract_id){
        return contractRepo.editContract(contract_id);
    }
    public List<Contract_DTO> getEndedContracts() {
        return contractRepo.getEndedContracts();
    }
    public List<Contract_DTO> getFutureContracts(){
        return contractRepo.getFutureContracts();
    }

    // Other lists
    public List<Contract_Type_Count> contractTypeCountDTOS() {
        return contractRepo.contractTypeCounts();
    }

    // Key values
    public Integer countAllCars(){
         return contractRepo.countAllCars();
    }
    public Integer activeContracts(){
        return contractRepo.activeContracts();
    }
    public Integer nrOfCarsInRepair(){
        return contractRepo.nrOfCarsInRepair();
    }
    public Double monthlyIncome(){
       return contractRepo.monthlyIncome();
    }

    // Insert methods
    public void addContract(Integer car_id, Integer customer_id, Integer car_model_lease_period_plan_id,
                            Integer car_model_max_km_plan, String start_date, Integer employee_id){

        contractRepo.addContract(car_id, customer_id, car_model_lease_period_plan_id,
                car_model_max_km_plan, start_date, employee_id);
    }

    public void updateEndDate(){
        contractRepo.updateEndDate();
    }

    // Update methods
    public void setEndDateToToday(Integer contract_id){
        contractRepo.setEndDateToToday(contract_id);
    }
    public void updateStartAndEndDate(Integer contract_id, String contract_start_date, String contract_end_date){
        contractRepo.updateStartAndEndDate(contract_id, contract_start_date, contract_end_date);
    }

    public Contract getContract(Integer id){
        return contractRepo.getContract(id);
    }

    public Integer getStartDateEndDateDiff(Integer contract_id){
        return contractRepo.getStartDateEndDateDiff(contract_id);
    }


}
