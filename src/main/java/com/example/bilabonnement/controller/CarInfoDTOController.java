package com.example.bilabonnement.controller;

import com.example.bilabonnement.repository.CarInfoDTORepo;
import com.example.bilabonnement.repository.CarRepo;
import com.example.bilabonnement.service.CarInfoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrazyController {
    @Autowired
    CarRepo carRepo;

    @Autowired
    CarInfoDTOService carInfoDTOService;



    @GetMapping("/carmanagement")
    String carmanagement(Model model){
        model.addAttribute("carInfoList", carInfoDTOService.getCarInfo());
        return "carmanagement";
    }





}
