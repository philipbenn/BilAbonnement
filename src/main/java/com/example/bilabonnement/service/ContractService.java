package com.example.bilabonnement.service;

import com.example.bilabonnement.model.contract.ContractDTO;
import com.example.bilabonnement.repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;

    public List<ContractDTO> getAllContractInfo(){
        return contractRepo.getAllContractInfo();
    }
}
