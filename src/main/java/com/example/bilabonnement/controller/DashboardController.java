package com.example.bilabonnement.controller;

import com.example.bilabonnement.repository.ContractTypeCountDTORepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
@Autowired
ContractTypeCountDTORepo contractTypeCountDTORepo;



    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
model.addAttribute("expiringContracts", contractTypeCountDTORepo.expiringContracts());
        int nrOfAvailableCars = contractTypeCountDTORepo.countAllCars() - contractTypeCountDTORepo.nrOfCarsInRepair() - contractTypeCountDTORepo.activeContracts();
model.addAttribute("nrOfAvailableCars", nrOfAvailableCars);
model.addAttribute("activeContracts" , contractTypeCountDTORepo.activeContracts());
model.addAttribute("monthlyIncome", contractTypeCountDTORepo.monthlyIncome());
model.addAttribute("contractTypeCount", contractTypeCountDTORepo.contractTypeCountDTOS());
model.addAttribute("nrOfCarsOfInRepair" , contractTypeCountDTORepo.nrOfCarsInRepair());
        return "dashboard";
    }



}
