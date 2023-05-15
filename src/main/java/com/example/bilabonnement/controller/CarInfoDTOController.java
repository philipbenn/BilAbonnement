package com.example.bilabonnement.controller;

import com.example.bilabonnement.repository.CarRepo;
import com.example.bilabonnement.service.CarInfoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarInfoDTOController {
    @Autowired
    CarRepo carRepo;
    @Autowired
    CarInfoDTOService carInfoDTOService;

    @GetMapping("/carmanagement")
    String carmanagement(Model model){
        model.addAttribute("carInfoList", carInfoDTOService.getCarInfo());
        return "carmanagement";
    }
    @GetMapping("/addCarModelForm")
    public String addCarModelForm(Model model){
        int nextCarModelId = carInfoDTOService.maxModelId();
        model.addAttribute("nextCarModelId", nextCarModelId);
        return "/addCarModelForm";
    }
    @PostMapping("/addCarModel")
    public String addCarModel(@RequestParam int nextCarModelId, @RequestParam String car_model){
        carInfoDTOService.addCarModel(car_model);
        nextCarModelId+=1;
        return "redirect:/addCarModelKmPlansForm/" + nextCarModelId;
    }
    @GetMapping("/addCarModelKmPlansForm/{car_model_id}")
    public String addCarModelKmPlans(@PathVariable int car_model_id, Model model){
        model.addAttribute("car_model_id", car_model_id);
        model.addAttribute("kmPlans", carInfoDTOService.getCarMaxKmPlans(car_model_id));
        model.addAttribute("lease_plans", carInfoDTOService.getCarLeasePeriodPlans(car_model_id));
        return "/addCarModelPlansForm";
    }
    @PostMapping("/addCarModelKmPlan")
    public String addCarModelKmPlan(@RequestParam int car_model_id, @RequestParam int max_km, @RequestParam int km_price_per_month){
        carInfoDTOService.addNewMaxKmPlan(car_model_id, max_km, km_price_per_month);
        return "redirect:/addCarModelKmPlansForm/"+ car_model_id;
    }

    @GetMapping("/addCarLeasePeriodPlanForm/{car_model_id}")
    public String addCarLeasePeriodPlanForm(@PathVariable int car_model_id, Model model){
        model.addAttribute("car_model_id", car_model_id);
        return "/addCarLeasePeriodPlanForm";
    }

    @PostMapping("/addCarLeasePlan")
    public String addCarLeasePlan(@RequestParam int car_model_id, @RequestParam String type, @RequestParam int price_per_month){
        carInfoDTOService.addCarLeasePlan(car_model_id, type, price_per_month);
        return "redirect:/addCarModelKmPlansForm/"+ car_model_id;
    }

    @PostMapping("/addNewCar")
    public String addNewCar(@RequestParam int car_model_id, @RequestParam String vognnummer){
        carInfoDTOService.registerCar(car_model_id, vognnummer);
        return "redirect:/carmanagement";
    }



}
