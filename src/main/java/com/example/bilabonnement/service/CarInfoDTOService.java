package com.example.bilabonnement.service;

import com.example.bilabonnement.model.CarInfoDTO;
import com.example.bilabonnement.model.Car_Lease_Period_Plan;
import com.example.bilabonnement.model.Car_Max_Km_Plan;
import com.example.bilabonnement.model.Car_Model;
import com.example.bilabonnement.repository.CarInfoDTORepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarInfoDTOService {
@Autowired
CarInfoDTORepo carInfoDTORepo;
     public List<CarInfoDTO> getCarInfo() {
     List<Car_Model> carModels = carInfoDTORepo.getCarModels();

     List<CarInfoDTO> carInfoList = new ArrayList<>();

        for (Car_Model carModel : carModels) {
            CarInfoDTO carInfo = new CarInfoDTO();
            carInfo.setCar_model(carModel);

            List<Car_Lease_Period_Plan> leasePeriodPlans = carInfoDTORepo.getCarLeasePeriodPlans(carModel.getCar_model_id());
            carInfo.setCar_lease_period_plans(leasePeriodPlans);

            List<Car_Max_Km_Plan> maxKmPlans = carInfoDTORepo.getCarMaxKmPlans(carModel.getCar_model_id());
            carInfo.setCar_max_km_plans(maxKmPlans);

            int carsLeasedOut = carInfoDTORepo.getCarsLeasedOut(carModel.getCar_model_id());
            carInfo.setCarsLeasedOut(carsLeasedOut);

            int carsInRepair = carInfoDTORepo.getCarsInRepair(carModel.getCar_model_id());
            carInfo.setCarsInRepair(carsInRepair);

            int carsInStock = carInfoDTORepo.getAllCarsCount(carModel.getCar_model_id());
            carsInStock = carsInStock - carsLeasedOut - carsInRepair;

            carInfo.setCarsInStock(carsInStock);

            carInfoList.add(carInfo);
        }

        return carInfoList;
    }

    public void addCarModel(String car_model){
        carInfoDTORepo.addCarModel(car_model);
    }

    //TODO Metode til at lave en ny max_km_plan til en car_model hvor metoden modtager et car_model_id
    public void addNewMaxKmPlan(int car_model_id, int max_km, int km_price_per_month){
        carInfoDTORepo.addNewMaxKmPlan(car_model_id, max_km, km_price_per_month);
    }

    //TODO Metode til at finde det nyeste car_model_id
    public int maxModelId() {
       return carInfoDTORepo.maxModelId();
    }

    public List<Car_Max_Km_Plan> getCarMaxKmPlans(int carModelId) {
        return carInfoDTORepo.getCarMaxKmPlans(carModelId);
    }
    public void addCarLeasePlan(int car_model_id, String type, int price_per_month){
        carInfoDTORepo.addCarLeasePlan(car_model_id, type, price_per_month);
    }

    public List<Car_Lease_Period_Plan> getCarLeasePeriodPlans(int carModelId) {
        return carInfoDTORepo.getCarLeasePeriodPlans(carModelId);
    }
    public void registerCar(int car_model_id, String vognnummer){
        carInfoDTORepo.registerCar(car_model_id, vognnummer);
    }
}
