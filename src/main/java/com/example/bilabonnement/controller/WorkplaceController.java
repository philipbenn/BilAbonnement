package com.example.bilabonnement.controller;

import com.example.bilabonnement.repository.WorkplaceRepo;
import com.example.bilabonnement.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkplaceController {

    @Autowired
    WorkplaceService workplaceService;

    @GetMapping("/damagereports")
    public String showDamageReports(Model model){
        model.addAttribute("getAllDamageInfo", workplaceService.getAllDamageInfo());
        return "/damagereports";
    }
}
