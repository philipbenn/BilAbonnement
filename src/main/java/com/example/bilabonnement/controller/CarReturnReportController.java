package com.example.bilabonnement.controller;

import com.example.bilabonnement.model.carReturnReport.Car_Return_Damage;
import com.example.bilabonnement.model.carReturnReport.Car_Return_Report;

import com.example.bilabonnement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarReturnReportController {
    @Autowired
    CarReturnReportRepo carReturnReportRepo;
    @Autowired
    CarRepo carRepo;
    @Autowired
    CarModelRepo carModelRepo;
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ContractRepo contractRepo;

    @GetMapping("/OpenDamageReport/{id}")
    public String editDamageReport(@PathVariable int id, Model model) {

        model.addAttribute("id", id);
        List<Car_Return_Damage> carReturnDamages = carReturnReportRepo.carReturnDamageFromReport(id);
        Car_Return_Report carReturnReport = carReturnReportRepo.getCarReturnReport(id);

        model.addAttribute("carReturnDamages", carReturnDamages);

        double totalprice = 0;

        for (int i = 0; i < carReturnDamages.size(); i++) {
            totalprice += carReturnDamages.get(i).getPrice();
        }

        model.addAttribute("totalprice", totalprice);

        return "/carReturnReport/editDamageReport";
    }

    @PostMapping("/editDamageReport")
    public String editDamageReport(@RequestParam int carReturnDamageId, @RequestParam int carReturnReportId, @RequestParam String damage_description, @RequestParam int is_fixed, @RequestParam double price) {

        Car_Return_Damage carReturnDamage = new Car_Return_Damage();

        carReturnDamage.setCar_return_damage_id(carReturnDamageId);
        carReturnDamage.setCar_return_report_id(carReturnReportId);
        carReturnDamage.setDamage_description(damage_description);
        carReturnDamage.setIsFixed(is_fixed);
        carReturnDamage.setPrice(price);

        carReturnReportRepo.editCarReturnDamage(carReturnDamage);

        return "redirect:/OpenDamageReport/" + carReturnReportId;
    }

    @PostMapping("/addCarReturnDamage")
    String addCarReturnDamage(@RequestParam int car_return_report_id) {

        Car_Return_Damage carReturnDamage = new Car_Return_Damage();
        carReturnDamage.setCar_return_report_id(car_return_report_id);
        carReturnDamage.setIsFixed(0);
        carReturnDamage.setDamage_description("..");

        carReturnReportRepo.addCarReturnDamage(carReturnDamage);

        return "redirect:/OpenDamageReport/" + car_return_report_id;

    }

    @GetMapping("/carReturnReports")
    String carReturnReports(Model model) {

        List<Car_Return_Report> carReturnReports = carReturnReportRepo.getAllCarReturnReports();

        model.addAttribute("carReturnReports", carReturnReports);

        return "/carReturnReport/carReturnReports";
    }

}
