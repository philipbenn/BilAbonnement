package com.example.bilabonnement.service;

import com.example.bilabonnement.model.CarInfoDTO;
import com.example.bilabonnement.model.Car_Lease_Period_Plan;
import com.example.bilabonnement.model.Car_Max_Km_Plan;
import com.example.bilabonnement.model.Car_Model;
import com.example.bilabonnement.repository.CarInfoDTORepo;
import org.springframework.beans.factory.annotation.Autowired;
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
}
