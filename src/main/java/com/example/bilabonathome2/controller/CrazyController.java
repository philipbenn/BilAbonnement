package com.example.bilabonathome2.controller;

import com.example.bilabonathome2.model.CarInfoDTO;
import com.example.bilabonathome2.model.Car_Lease_Period_Plan;
import com.example.bilabonathome2.model.Car_Max_Km_Plan;
import com.example.bilabonathome2.model.Car_Model;
import com.example.bilabonathome2.repository.CarInfoDTORepo;
import com.example.bilabonathome2.repository.CarRepo;
import com.example.bilabonathome2.service.CarInfoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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
