package com.example.bilabonnement.controller;


import com.example.bilabonnement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {
@Autowired
CarService carService;

    // Views
    @GetMapping("/addCarForm/{car_model_id}")
    public String addCarForm(@PathVariable Integer car_model_id, Model model){
        model.addAttribute("car_model_id", car_model_id);
        return "car/addCarForm";
    }

    // Post Methods
    @PostMapping("/addNewCar")
    public String addNewCar(@RequestParam Integer car_model_id, @RequestParam String vognnummer){
        carService.registerCar(car_model_id, vognnummer);
        return "redirect:/carModelsOverview";
    }

    @GetMapping("/registeredCarsOverview/{car_model_id}/{car_model_name}")
    public String registeredCarsOverview(Model model, @PathVariable Integer car_model_id, @PathVariable String car_model_name){
        model.addAttribute("registeredCars", carService.getCarsByCarModelId(car_model_id));
        model.addAttribute("car_model_name", car_model_name);
        return "car/registeredCarsOverview";
    }

}
