package com.example.bilabonnement.controller;

import com.example.bilabonnement.model.carModel.Car_Model_Lease_Period_Plan;
import com.example.bilabonnement.model.carModel.Car_Model_Max_Km_Plan;
import com.example.bilabonnement.model.carReturnReport.Car_Return_Damage;

import com.example.bilabonnement.model.carReturnReport.Car_Return_Report;
import com.example.bilabonnement.model.carReturnReport.Car_Return_Report_DTO;
import com.example.bilabonnement.model.contract.Contract;
import com.example.bilabonnement.repository.CarModelRepo;
import com.example.bilabonnement.service.CarModelService;
import com.example.bilabonnement.service.CarReturnReportService;
import com.example.bilabonnement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
    CarReturnReportService carReturnReportService;
    @Autowired
    CarModelService carModelService;
    @Autowired
    ContractService contractService;
    @Autowired
    JdbcTemplate jdbcTemplate;


    // Views

    @GetMapping("/carReturnReports/pending")
    String pendingCarReturnReports(Model model) {

        autoMakeCarReturnReportsForExpiredContracts();

        String info = "SKADESRAPPORTER DER SKAL BEHANDLES";

        List<Car_Return_Report_DTO> carReturnReports = carReturnReportService.getAllPendingCarReturnReports();

        model.addAttribute("info", info);
        model.addAttribute("carReturnReports", carReturnReports);


        return "/carReturnReport/carReturnReports";
    }

    @GetMapping("/carReturnReports/closed")
    String closedCarReturnReports(Model model) {

        String info = "AFSLUTTEDE SKADESRAPPORTER";

        List<Car_Return_Report_DTO> carReturnReports = carReturnReportService.getAllClosedCarReturnReports();


        model.addAttribute("info", info);

        model.addAttribute("carReturnReports", carReturnReports);
        return "/carReturnReport/carReturnReportsClosed";
    }

    @GetMapping("/openDamageReport/{id}")
    public String editDamageReport(@PathVariable Integer id, Model model) {

        model.addAttribute("id", id);
        List<Car_Return_Damage> carReturnDamages = carReturnReportService.carReturnDamageFromReport(id);

        Car_Return_Report  carReturnReport = carReturnReportService.getCarReturnReport(id);

        Contract contract =  contractService.getContract(carReturnReport.getContract_id());

        Car_Model_Lease_Period_Plan carModelLeasePeriodPlan = carModelService.carModelLeasePeriodPlan(contract.getCar_model_lease_period_plan_id());

        Car_Model_Max_Km_Plan carModelMaxKmPlan = carModelService.carModelMaxKmPlan(contract.getCar_model_max_km_plan_id());

        System.out.println(
                contract.getCar_model_max_km_plan_id());

        Integer totalKmAllowed = contractService.getStartDateEndDateDiff(contract.getContract_id()) * carModelMaxKmPlan.getMax_km();
        Integer totalKmDriven = carReturnReport.getKm_driven();

        Integer totalKmOver = 0;

        if (totalKmDriven!=null) {
             totalKmOver = totalKmDriven - totalKmAllowed;
        }

        Double totalKmOverPrice = 0.0;
        if (totalKmOver > 0) { totalKmOverPrice = totalKmOver * 0.75;}


        model.addAttribute("totalKmAllowed", totalKmAllowed);
        model.addAttribute("totalKmDriven", totalKmDriven);
        model.addAttribute("totalKmOver", totalKmOver);
        model.addAttribute("totalKmOverPrice", totalKmOverPrice);

        model.addAttribute("carReturnReport", carReturnReport);
        model.addAttribute("carReturnDamages", carReturnDamages);

        double totalPrice = 0;


        for (Car_Return_Damage carReturnDamage : carReturnDamages) {
            totalPrice += carReturnDamage.getPrice();
        }

        totalPrice += totalKmOverPrice;

        model.addAttribute("totalPrice", totalPrice);

        return "/carReturnReport/editDamageReport";
    }

    @GetMapping("/openDamageReportNoEdit/{id}")
    public String editDamageReportNoEdit(@PathVariable Integer id, Model model) {

        model.addAttribute("id", id);
        List<Car_Return_Damage> carReturnDamages = carReturnReportService.carReturnDamageFromReport(id);

        model.addAttribute("carReturnDamages", carReturnDamages);

        double totalPrice = 0;

        for (Car_Return_Damage carReturnDamage : carReturnDamages) {
            totalPrice += carReturnDamage.getPrice();
        }

        model.addAttribute("totalPrice", totalPrice);

        return "/carReturnReport/editDamageReportNoEdit";
    }

    // Post Methods
    @PostMapping("/editDamageReport")
    public String editDamageReport(@RequestParam Integer carReturnDamageId, @RequestParam Integer carReturnReportId, @RequestParam String damage_description, @RequestParam Integer is_fixed, @RequestParam double price) {

        Car_Return_Damage carReturnDamage = new Car_Return_Damage();

        carReturnDamage.setCar_return_damage_id(carReturnDamageId);
        carReturnDamage.setCar_return_report_id(carReturnReportId);
        carReturnDamage.setDamage_description(damage_description);
        carReturnDamage.setIsFixed(is_fixed);
        carReturnDamage.setPrice(price);


        carReturnReportService.editCarReturnDamage(carReturnDamage);

        return "redirect:/openDamageReport/" + carReturnReportId;
    }

    @PostMapping("/addCarReturnDamage")
    String addCarReturnDamage(@RequestParam Integer car_return_report_id) {

        Car_Return_Damage carReturnDamage = new Car_Return_Damage();
        carReturnDamage.setCar_return_report_id(car_return_report_id);
        carReturnDamage.setIsFixed(0);
        carReturnDamage.setDamage_description("..");



        carReturnReportService.addCarReturnDamage(carReturnDamage);

        return "redirect:/openDamageReport/" + car_return_report_id;
    }

    @PostMapping("/updateKmDriven")
    String updateKmDriven(@RequestParam Integer car_return_report_id, @RequestParam Integer km_driven){

        carReturnReportService.updateKmDriven(car_return_report_id, km_driven);

        return "redirect:/openDamageReport/" + car_return_report_id;
    }

    public void autoMakeCarReturnReportsForExpiredContracts(){
        String sqlSelectAllContracts = "SELECT * FROM contract WHERE end_date <= NOW() + INTERVAL 1 HOUR";
        List<Contract> contracts = jdbcTemplate.query(sqlSelectAllContracts, new BeanPropertyRowMapper<>(Contract.class));
        for (int i = 0; i<contracts.size(); i++){
            String sqlCheckIfReportExists = "SELECT * FROM car_return_report WHERE contract_id = ?";
            List<Car_Return_Report> car_return_reports = jdbcTemplate.query(sqlCheckIfReportExists, new BeanPropertyRowMapper<>(Car_Return_Report.class), contracts.get(i).getContract_id());
            if (car_return_reports.size() == 0){
                String sqlInsertCarReturnReport = "INSERT INTO car_return_report (car_id, km_driven, contract_id) VALUES (?, ?, ?)";
                jdbcTemplate.update(sqlInsertCarReturnReport, contracts.get(i).getCar_id(), 0, contracts.get(i).getContract_id());
                Car_Return_Damage car_return_damage = new Car_Return_Damage();
                car_return_damage.setDamage_description("Sæt denne til 'fixed' når skadesrapporten er færdig");
                car_return_damage.setCar_return_report_id(carReturnReportService.getMaxCarReturnReportId());
                car_return_damage.setIsFixed(0);
                car_return_damage.setPrice(0);
                carReturnReportService.addCarReturnDamage(car_return_damage);
            }
        }
    }

}
