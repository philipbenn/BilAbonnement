package com.example.bilabonnement.service;

import com.example.bilabonnement.model.ContractInfo;
import com.example.bilabonnement.model.Customer;
import com.example.bilabonnement.repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;


    public List<ContractInfo> getAllContractInfo(){
        return contractRepo.getAllContractInfo();
    }

    public List<Customer> getAllCustomer(){
        return contractRepo.getAllCustomer();
    }


}
