package com.example.bilabonathome2.controller;

import com.example.bilabonathome2.model.CarModelInfo;
import com.example.bilabonathome2.model.ContractInfo;
import com.example.bilabonathome2.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CrazyController {
    @Autowired
    CarRepo carRepo;


    @GetMapping("/carmenu")
    String carmenu(){
        return "carmenu";
    }
    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    @GetMapping("/showAllCars")
    public String showAllCars(Model model){
        List<CarModelInfo> carModelInfo = carRepo.getAllCarInfo();

        model.addAttribute("cars" , carModelInfo);

        return "showAllCars";
    }

}
