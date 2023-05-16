package com.example.bilabonnement.controller;

import com.example.bilabonnement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ContractController {

    @Autowired
    ContractService contractService;

    @GetMapping("/contractoverview")
    public String showContract(Model model){
        model.addAttribute( "getContractInfo", contractService.getAllContractInfo());
        return "/contracts";
    }

    @GetMapping("/addContractForm/{customer_id}")
    public String addContractForm(@PathVariable int customer_id, Model model){
        model.addAttribute("customer_id", customer_id);
        return "/addContractForm";
    }

    //TODO postmapping(add contract) ReqeustParam customer_id, de andre felter  request Param
}
