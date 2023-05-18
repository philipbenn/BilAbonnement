package com.example.bilabonnement.controller;

import com.example.bilabonnement.model.carReturnReport.Car_Return_Damage;
import com.example.bilabonnement.repository.CarReturnReportRepo;
import com.example.bilabonnement.service.CarModelService;
import com.example.bilabonnement.service.CarService;
import com.example.bilabonnement.service.ContractService;
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
    CarModelService carModelService;
    @Autowired
    CarService carService;
    @Autowired
    CarReturnReportRepo carReturnReportRepo;

    @GetMapping("/contractsOverview")
    public String showContract(Model model){
        model.addAttribute( "getContractInfo", contractService.getAllContractInfo());
        return "/contract/contractsOverview";
    }

    @GetMapping ("/addContractForm1/{customer_id}")
    public String addContractForm(Model model, @PathVariable int customer_id){
       model.addAttribute("customer_id", customer_id);
       model.addAttribute("car_model_list", contractService.getAllCarModels());
        return "contract/addContractForm1";
    }

    @PostMapping("/addContract")
    public String addContract(@RequestParam int customer_id, @RequestParam int car_id,
                              @RequestParam int car_model_lease_period_plan_id, @RequestParam int car_model_max_km_plan_id,
                              @RequestParam String start_date, @RequestParam int employee_id){

        contractService.addContract(car_id, customer_id, car_model_lease_period_plan_id, car_model_max_km_plan_id,
                                 start_date, employee_id);

        return "redirect:/contractsOverview";
    }


    @GetMapping ("/addContractForm2")
    public String test(Model model, @RequestParam int customer_id, @RequestParam int car_model_id){
        model.addAttribute("car_model_id", car_model_id);
        model.addAttribute("customer_id" , customer_id);
        model.addAttribute("km_plans", carModelService.getCarModelMaxKmPlans(car_model_id));
        model.addAttribute("lease_plans", carModelService.getCarModelLeasePeriodPlans(car_model_id));
        model.addAttribute("cars", carService.getCarsByCarModelId(car_model_id));

        //TODO calculate end date by adding lease period to start date
        //TODO filter out cars that are already in a contract

        return "contract/addContractForm2";
    }

    @PostMapping ("/endContract")
    public String endContract(@RequestParam int contract_id, @RequestParam int car_id){


        //Set end date of contract to today
        contractService.setEndDateToToday(contract_id);
        //Create new CarReturnDamageReport with this contract_id
        carReturnReportRepo.addCarReturnReport(contract_id, car_id);
        //Add an empty CarReturnDamage to the CarReturnDamageReport
        Car_Return_Damage car_return_damage = new Car_Return_Damage();
        car_return_damage.setDamage_description("Test damage. Please edit.");
        car_return_damage.setCar_return_report_id(carReturnReportRepo.getMaxCarReturnReportId());
        car_return_damage.setIsFixed(0);
        car_return_damage.setPrice(0);
        carReturnReportRepo.addCarReturnDamage(car_return_damage);
        //Open the CarReturnDamageReport in the browser
        return "redirect:/OpenDamageReport/" + carReturnReportRepo.getMaxCarReturnReportId();
    }
}
