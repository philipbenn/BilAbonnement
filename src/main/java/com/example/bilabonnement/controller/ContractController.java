package com.example.bilabonnement.controller;

import com.example.bilabonnement.repository.CarInfoDTORepo;
import com.example.bilabonnement.repository.ContractRepo;
import com.example.bilabonnement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContractController {

    @Autowired
    ContractService contractService;
    @Autowired
    CarInfoDTORepo carInfoDTORepo;
    @Autowired
    ContractRepo contractRepo;




    @GetMapping("/contractoverview")
    public String showContract(Model model){
        model.addAttribute( "getContractInfo", contractService.getAllContractInfo());
        return "/contracts";
    }

    @GetMapping ("/addContractForm1/{customer_id}")
    public String addContractForm(Model model, @PathVariable int customer_id){
       model.addAttribute("customer_id", customer_id);
       model.addAttribute("car_model_list", contractRepo.getAllCarModels());
        return "/addContractForm1";
    }

    @PostMapping("/addContract")
    public String addContract(Model model, @RequestParam int customer_id, @RequestParam int car_id,
                              @RequestParam int car_lease_period_plan_id, @RequestParam int car_max_km_plan_id,
                              @RequestParam String start_date, @RequestParam int employee_id){

        contractRepo.addContract(car_id, customer_id, car_lease_period_plan_id, car_max_km_plan_id,
                                 start_date, employee_id);

        return "redirect:/contractoverview";
    }


    @GetMapping ("/test")
    public String test(Model model, @RequestParam int customer_id, @RequestParam int car_model_id){
        model.addAttribute("car_model_id", car_model_id);
        model.addAttribute("customer_id" , customer_id);
        model.addAttribute("km_plans", carInfoDTORepo.getCarMaxKmPlans(car_model_id));
        model.addAttribute("lease_plans", carInfoDTORepo.getCarLeasePeriodPlans(car_model_id));
        model.addAttribute("cars", carInfoDTORepo.getCarsByCarModelId(car_model_id));

        //TODO calculate end date by adding lease period to start date

        //TODO filter out cars that are already in a contract

        return "test";
    }

}
