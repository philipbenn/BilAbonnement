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
    public String addCarForm(@PathVariable int car_model_id, Model model){
        model.addAttribute("car_model_id", car_model_id);
        return "car/addCarForm";
    }

    // Post Methods
    @PostMapping("/addNewCar")
    public String addNewCar(@RequestParam int car_model_id, @RequestParam String vognnummer){
        carService.registerCar(car_model_id, vognnummer);
        return "redirect:/carModelsOverview";
    }
}
