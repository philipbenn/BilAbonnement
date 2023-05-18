package com.example.bilabonnement.service;

import com.example.bilabonnement.model.contract.ContractDTO;
import com.example.bilabonnement.model.contract.ContractTypeCount;
import com.example.bilabonnement.repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    ContractRepo contractRepo;

    // ContractDTO lists
    public List<ContractDTO> expiringContracts(){

        return contractRepo.expiringContracts();
    }

    public List<ContractDTO> getCustomerHistory(int customer_id){
        return contractRepo.getCustomerHistory(customer_id);
    }
    public List<ContractDTO> getActiveContracts() {
        return contractRepo.getActiveContracts();
    }
    public List<ContractDTO> editContract(int contract_id){
        return contractRepo.editContract(contract_id);
    }
    public List<ContractDTO> getEndedContracts() {
        return contractRepo.getEndedContracts();
    }
    public List<ContractDTO> getFutureContracts(){
        return contractRepo.getFutureContracts();
    }

    // Other lists
    public List<ContractTypeCount> contractTypeCountDTOS() {
        return contractRepo.contractTypeCounts();
    }

    // Key values
    public int countAllCars(){
         return contractRepo.countAllCars();
    }
    public int activeContracts(){
        return contractRepo.activeContracts();
    }
    public int nrOfCarsInRepair(){
        return contractRepo.nrOfCarsInRepair();
    }
    public Double monthlyIncome(){
       return contractRepo.monthlyIncome();
    }

    // Insert methods
    public void addContract(int car_id, int customer_id, int car_model_lease_period_plan_id,
                            int car_model_max_km_plan, String start_date, int employee_id){

        contractRepo.addContract(car_id, customer_id, car_model_lease_period_plan_id,
                car_model_max_km_plan, start_date, employee_id);
    }

    public void updateEndDate(){
        contractRepo.updateEndDate();
    }

    // Update methods
    public void setEndDateToToday(int contract_id){
        contractRepo.setEndDateToToday(contract_id);
    }
    public void updateStartAndEndDate(int contract_id, String contract_start_date, String contract_end_date){
        contractRepo.updateStartAndEndDate(contract_id, contract_start_date, contract_end_date);
    }
}
