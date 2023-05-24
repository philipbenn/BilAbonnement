package com.example.bilabonnement.service;

import com.example.bilabonnement.model.carReturnReport.Car_Return_Damage;
import com.example.bilabonnement.model.carReturnReport.Car_Return_Report;
import com.example.bilabonnement.model.carReturnReport.Car_Return_Report_DTO;
import com.example.bilabonnement.repository.CarReturnReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarReturnReportService {
@Autowired
    CarReturnReportRepo carReturnReportRepo;

    // Get methods

    public Car_Return_Report getCarReturnReport (Integer car_return_report_id){
        return carReturnReportRepo.carReturnReport(car_return_report_id);
    }

    public List<Car_Return_Damage> carReturnDamageFromReport (Integer car_return_report_id){
        return carReturnReportRepo.carReturnDamageFromReport(car_return_report_id);
      }

    public void updateKmDriven(Integer carReturnReportId, Integer kmDriven) {
        carReturnReportRepo.updateKmDriven(carReturnReportId,kmDriven);
    }

    public List <Car_Return_Report_DTO> getAllPendingCarReturnReports(){
       return carReturnReportRepo.getAllPendingCarReturnReports();
    }

    public List <Car_Return_Report_DTO> getAllClosedCarReturnReports(){
           return carReturnReportRepo.getAllClosedCarReturnReports();
    }

    // Insert methods
    public void addCarReturnReport (Integer contract_id, Integer car_id){
    carReturnReportRepo.addCarReturnReport(contract_id,car_id);
    }
    public void addCarReturnDamage (Car_Return_Damage car_return_damage){
 carReturnReportRepo.addCarReturnDamage(car_return_damage);
    }

    // Update Methods
    public void editCarReturnDamage (Car_Return_Damage car_return_damage){
          carReturnReportRepo.editCarReturnDamage(car_return_damage);
    }

    // Key Values
    public Integer getMaxCarReturnReportId (){
    return carReturnReportRepo.getMaxCarReturnReportId();
    }
}
