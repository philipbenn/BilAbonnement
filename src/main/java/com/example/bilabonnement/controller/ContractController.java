package com.example.bilabonnement.controller;

import com.example.bilabonnement.repository.CarModelRepo;
import com.example.bilabonnement.repository.CarRepo;
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
    CarModelRepo carModelRepo;

    @Autowired
    CarRepo carRepo;
    @Autowired
    ContractRepo contractRepo;


    @GetMapping("/contractsOverview")
    public String showContract(Model model){
        model.addAttribute( "getContractInfo", contractService.getAllContractInfo());
        return "/contract/contractsOverview";
    }

    @GetMapping ("/addContractForm1/{customer_id}")
    public String addContractForm(Model model, @PathVariable int customer_id){
       model.addAttribute("customer_id", customer_id);
       model.addAttribute("car_model_list", contractRepo.getAllCarModels());
        return "contract/addContractForm1";
    }

    @PostMapping("/addContract")
    public String addContract(@RequestParam int customer_id, @RequestParam int car_id,
                              @RequestParam int car_model_lease_period_plan_id, @RequestParam int car_model_max_km_plan_id,
                              @RequestParam String start_date, @RequestParam int employee_id){

        contractRepo.addContract(car_id, customer_id, car_model_lease_period_plan_id, car_model_max_km_plan_id,
                                 start_date, employee_id);

        return "redirect:/contractsOverview";
    }


    @GetMapping ("/addContractForm2")
    public String test(Model model, @RequestParam int customer_id, @RequestParam int car_model_id){
        model.addAttribute("car_model_id", car_model_id);
        model.addAttribute("customer_id" , customer_id);
        model.addAttribute("km_plans", carModelRepo.getCarModelMaxKmPlans(car_model_id));
        model.addAttribute("lease_plans", carModelRepo.getCarModelLeasePeriodPlans(car_model_id));
        model.addAttribute("cars", carRepo.getCarsByCarModelId(car_model_id));

        //TODO calculate end date by adding lease period to start date
        //TODO filter out cars that are already in a contract

        return "contract/addContractForm2";
    }

}
