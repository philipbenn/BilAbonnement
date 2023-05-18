package com.example.bilabonnement.controller;

import com.example.bilabonnement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    ContractService contractService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        model.addAttribute("expiringContracts", contractService.expiringContracts());
        // total number of cars - number of cars in repair - number of active contracts = number of available cars
        int nrOfAvailableCars = contractService.countAllCars() - contractService.nrOfCarsInRepair() - contractService.activeContracts();
        model.addAttribute("nrOfAvailableCars", nrOfAvailableCars);
        model.addAttribute("activeContracts", contractService.activeContracts());
        model.addAttribute("monthlyIncome", contractService.monthlyIncome());
        model.addAttribute("contractTypeCounts", contractService.contractTypeCountDTOS());
        model.addAttribute("nrOfCarsOfInRepair", contractService.nrOfCarsInRepair());
        return "/dashboard/dashboard";
    }
}
