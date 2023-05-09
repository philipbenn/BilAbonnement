package com.example.bilabonnement.controller;

import com.example.bilabonnement.repository.ContractRepo;
import com.example.bilabonnement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractController {

    @Autowired
    ContractService contractService;


    @GetMapping("/contract")
    public String showContract(Model model){
        model.addAttribute( "getContractInfo", contractService.getAllContractInfo());
        return "/contracts";
    }




}
