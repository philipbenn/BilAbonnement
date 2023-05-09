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
        int nextCarModelId = 0;
        model.addAttribute("nextCarModelId", nextCarModelId);
        return "/addcarmodelform";
    }
    @PostMapping("/addCarModel")
    public String addCarModel(@RequestParam int nextCarModelId, @RequestParam String car_model){
        //Repo.addCar
        return "/addCarModelKmPlansForm/" + nextCarModelId;
    }
    @GetMapping("/addCarModelKmPlansForm/{car_model_id}")
    public String addCarModelKmPlans(@PathVariable int car_model_id, Model model){
        model.addAttribute(car_model_id);
        model.addAttribute("kmPlans", "kmPlans");
        return "";
    }
    @PostMapping("/addCarModelKmPlan")
    public String addCarModelKmPlan(@RequestParam int car_model_id, @RequestParam int max_km, @RequestParam int price){
        //repo.addKmPlan
        return "/addCarModelKmPlanForm/"+ car_model_id;
    }





}
