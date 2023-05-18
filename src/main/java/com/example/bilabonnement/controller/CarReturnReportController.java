package com.example.bilabonnement.controller;

import com.example.bilabonnement.model.carReturnReport.Car_Return_Damage;

import com.example.bilabonnement.model.carReturnReport.Car_Return_Report_DTO;
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

    // Views
    @GetMapping("/carReturnReports/pending")
    String pendingCarReturnReports(Model model) {

        String info = "SKADESRAPPORTER DER SKAL BEHANDLES";

        List<Car_Return_Report_DTO> carReturnReports = carReturnReportRepo.getAllPendingCarReturnReports();


        model.addAttribute("info", info);
        model.addAttribute("carReturnReports", carReturnReports);
        return "/carReturnReport/carReturnReports";
    }

    @GetMapping("/carReturnReports/closed")
    String closedCarReturnReports(Model model) {

        String info = "AFSLUTTEDE SKADESRAPPORTER";

        List<Car_Return_Report_DTO> carReturnReports = carReturnReportRepo.getAllClosedCarReturnReports();


        model.addAttribute("info", info);
        model.addAttribute("carReturnReports", carReturnReports);
        return "/carReturnReport/carReturnReports";
    }
    @GetMapping("/openDamageReport/{id}")
    public String editDamageReport(@PathVariable int id, Model model) {

        model.addAttribute("id", id);
        List<Car_Return_Damage> carReturnDamages = carReturnReportRepo.carReturnDamageFromReport(id);

        model.addAttribute("carReturnDamages", carReturnDamages);

        double totalPrice = 0;

        for (Car_Return_Damage carReturnDamage : carReturnDamages) {
            totalPrice += carReturnDamage.getPrice();
        }

        model.addAttribute("totalPrice", totalPrice);

        return "/carReturnReport/editDamageReport";
    }

    // Post Methods
    @PostMapping("/editDamageReport")
    public String editDamageReport(@RequestParam int carReturnDamageId, @RequestParam int carReturnReportId, @RequestParam String damage_description, @RequestParam int is_fixed, @RequestParam double price) {

        Car_Return_Damage carReturnDamage = new Car_Return_Damage();

        carReturnDamage.setCar_return_damage_id(carReturnDamageId);
        carReturnDamage.setCar_return_report_id(carReturnReportId);
        carReturnDamage.setDamage_description(damage_description);
        carReturnDamage.setIsFixed(is_fixed);
        carReturnDamage.setPrice(price);

        carReturnReportRepo.editCarReturnDamage(carReturnDamage);

        return "redirect:/openDamageReport/" + carReturnReportId;
    }
    @PostMapping("/addCarReturnDamage")
    String addCarReturnDamage(@RequestParam int car_return_report_id) {

        Car_Return_Damage carReturnDamage = new Car_Return_Damage();
        carReturnDamage.setCar_return_report_id(car_return_report_id);
        carReturnDamage.setIsFixed(0);
        carReturnDamage.setDamage_description("..");

        carReturnReportRepo.addCarReturnDamage(carReturnDamage);

        return "redirect:/openDamageReport/" + car_return_report_id;

    }

}
