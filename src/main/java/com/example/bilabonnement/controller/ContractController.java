package com.example.bilabonnement.controller;

import com.example.bilabonnement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContractController {

    @Autowired
    ContractService contractService;

    @GetMapping("/contractoverview")
    public String showContract(Model model){
        model.addAttribute( "getContractInfo", contractService.getAllContractInfo());
        return "/contracts";
    }
}
