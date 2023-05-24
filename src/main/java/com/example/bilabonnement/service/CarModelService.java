package com.example.bilabonnement.service;

import com.example.bilabonnement.model.carModel.Car_Model_DTO;
import com.example.bilabonnement.model.carModel.Car_Model_Lease_Period_Plan;
import com.example.bilabonnement.model.carModel.Car_Model_Max_Km_Plan;
import com.example.bilabonnement.model.carModel.Car_Model;
import com.example.bilabonnement.repository.CarModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarModelService {
@Autowired
CarModelRepo carModelRepo;

    // Lists
    public List<Car_Model_DTO> getCarInfo() {
        List<Car_Model> carModels = carModelRepo.getCarModels();

        List<Car_Model_DTO> carInfoList = new ArrayList<>();

        for (Car_Model carModel : carModels) {
            Car_Model_DTO carInfo = new Car_Model_DTO();
            carInfo.setCar_model(carModel);

            List<Car_Model_Lease_Period_Plan> carModelLeasePeriodPlans = carModelRepo.getCarModelLeasePeriodPlans(carModel.getCar_model_id());
            carInfo.setCar_model_lease_period_plans(carModelLeasePeriodPlans);

            List<Car_Model_Max_Km_Plan> carModelMaxKmPlans = carModelRepo.getCarModelMaxKmPlans(carModel.getCar_model_id());
            carInfo.setCar_model_max_km_plans(carModelMaxKmPlans);

            Integer carsLeasedOut = carModelRepo.getCarsLeasedOutCount(carModel.getCar_model_id());
            carInfo.setCarsLeasedOut(carsLeasedOut);

            Integer carsInRepair = carModelRepo.getCarsInRepairCount(carModel.getCar_model_id());
            carInfo.setCarsInRepair(carsInRepair);

            Integer carsInStock = carModelRepo.getAllCarsCount(carModel.getCar_model_id());
            carsInStock = carsInStock - carsLeasedOut - carsInRepair;

            carInfo.setCarsInStock(carsInStock);

            carInfoList.add(carInfo);
        }
        return carInfoList;
    }


    public List<Car_Model> getCarModels() {
     return carModelRepo.getCarModels();
    }

    public List<Car_Model_Max_Km_Plan> getCarModelMaxKmPlans(Integer carModelId) {
        return carModelRepo.getCarModelMaxKmPlans(carModelId);
    }
    public List<Car_Model_Lease_Period_Plan> getCarModelLeasePeriodPlans(Integer carModelId) {
        return carModelRepo.getCarModelLeasePeriodPlans(carModelId);
    }

    // Insert methods
    public void addCarModel(String car_model){
        carModelRepo.addCarModel(car_model);
    }

    public void addNewCarModelMaxKmPlan(Integer car_model_id, Integer max_km, Integer km_price_per_month){
        carModelRepo.addCarModelMaxKmPlan(car_model_id, max_km, km_price_per_month);
    }
    public void addCarModelLeasePlan(Integer car_model_id, String type, Integer price_per_month, Integer nrOfMonths){
        carModelRepo.addCarModelLeasePlan(car_model_id, type, price_per_month, nrOfMonths);
    }

    // Key values
    public Integer maxModelId() {
       return carModelRepo.maxModelId();
    }


    // Get methods
    public Car_Model getCarModel(Integer car_model_id){
        return carModelRepo.getCarModel(car_model_id);
    }

    public Car_Model_Lease_Period_Plan carModelLeasePeriodPlan(Integer id){
    return carModelRepo.carModelLeasePeriodPlan(id);
    }

    public Car_Model_Max_Km_Plan carModelMaxKmPlan(Integer id){
    return carModelRepo.carModelMaxKmPlan(id);
    }

}
