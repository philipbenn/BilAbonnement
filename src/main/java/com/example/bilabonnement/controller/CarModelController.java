package com.example.bilabonnement.controller;

import com.example.bilabonnement.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarModelController {

    @Autowired
    CarModelService carModelService;


    @GetMapping("/carModelsOverview")
    String carmanagement(Model model){
        model.addAttribute("carInfoList", carModelService.getCarInfo());
        return "carModel/carModelsOverview";
    }
    @GetMapping("/addCarModelForm")
    public String addCarModelForm(Model model){
        int nextCarModelId = carModelService.maxModelId();
        model.addAttribute("nextCarModelId", nextCarModelId);
        return "carModel/addCarModelForm";
    }
    @PostMapping("/addCarModel")
    public String addCarModel(@RequestParam int nextCarModelId, @RequestParam String car_model){
        carModelService.addCarModel(car_model);
        nextCarModelId+=1;
        return "redirect:/addCarModelPlansForm/" + nextCarModelId;
    }
    @GetMapping("/addCarModelPlansForm/{car_model_id}")
    public String addCarModelKmPlans(@PathVariable int car_model_id, Model model){

        model.addAttribute("carModel", carModelService.getCarModel(car_model_id));
        model.addAttribute("car_model_id", car_model_id);
        model.addAttribute("kmPlans", carModelService.getCarModelMaxKmPlans(car_model_id));
        model.addAttribute("lease_plans", carModelService.getCarModelLeasePeriodPlans(car_model_id));
        return "carModel/addCarModelPlansForm";
    }
    @PostMapping("/addCarModelKmPlan")
    public String addCarModelKmPlan(@RequestParam int car_model_id, @RequestParam int max_km, @RequestParam int km_price_per_month){
        carModelService.addNewCarModelMaxKmPlan(car_model_id, max_km, km_price_per_month);
        return "redirect:/addCarModelPlansForm/"+ car_model_id;
    }

    @PostMapping("/addCarLeasePlan")
    public String addCarLeasePlan(@RequestParam int car_model_id, @RequestParam String type, @RequestParam int price_per_month, @RequestParam int nrOfMonths){
        carModelService.addCarModelLeasePlan(car_model_id, type, price_per_month, nrOfMonths);
        return "redirect:/addCarModelPlansForm/"+ car_model_id;
    }
}
