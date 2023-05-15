package com.example.bilabonnement.controller;

import com.example.bilabonnement.repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    ContractRepo contractRepo;
    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        model.addAttribute("expiringContracts", contractRepo.expiringContracts());
        int nrOfAvailableCars = contractRepo.countAllCars() - contractRepo.nrOfCarsInRepair() - contractRepo.activeContracts();
        model.addAttribute("nrOfAvailableCars", nrOfAvailableCars);
        model.addAttribute("activeContracts", contractRepo.activeContracts());
        model.addAttribute("monthlyIncome", contractRepo.monthlyIncome());
        model.addAttribute("contractTypeCount", contractRepo.contractTypeCountDTOS());
        model.addAttribute("nrOfCarsOfInRepair", contractRepo.nrOfCarsInRepair());
        return "/dashboard/dashboard";
    }
}
