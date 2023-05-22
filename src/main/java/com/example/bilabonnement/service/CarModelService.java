package com.example.bilabonnement.service;

import com.example.bilabonnement.model.carModel.CarModelDTO;
import com.example.bilabonnement.model.carModel.Car_Model_Lease_Period_Plan;
import com.example.bilabonnement.model.carModel.Car_Model_Max_Km_Plan;
import com.example.bilabonnement.model.carModel.Car_Model;
import com.example.bilabonnement.repository.CarModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarModelService {
@Autowired
CarModelRepo carModelRepo;

// Lists

    // Henter bilinformation for alle bilmodeller.
    // For hver bilmodel hentes følgende information:
    // - Bilmodelobjektet selv.
    // - Liste over lejeperiodeplaner for bilmodellen.
    // - Liste over maksimal kilometerplaner for bilmodellen.
    // - Antallet af udlejede biler af bilmodellen.
    // - Antallet af biler i reparation af bilmodellen.
    // - Antallet af biler på lager af bilmodellen.
    // Alle disse oplysninger samles i en liste af CarModelDTO-objekter
    public List<CarModelDTO> getCarInfo() {

        List<Car_Model> carModels = carModelRepo.getCarModels();

        List<CarModelDTO> carModelDTOList = new ArrayList<>();

        for (Car_Model carModel : carModels) {
            CarModelDTO carModelDTO = new CarModelDTO();
            carModelDTO.setCar_model(carModel);

            List<Car_Model_Lease_Period_Plan> carModelLeasePeriodPlans = carModelRepo.getCarModelLeasePeriodPlans(carModel.getCar_model_id());
            carModelDTO.setCar_model_lease_period_plans(carModelLeasePeriodPlans);

            List<Car_Model_Max_Km_Plan> carModelMaxKmPlans = carModelRepo.getCarModelMaxKmPlans(carModel.getCar_model_id());
            carModelDTO.setCar_model_max_km_plans(carModelMaxKmPlans);

            int carsLeasedOut = carModelRepo.getCarsLeasedOutCountByModelId(carModel.getCar_model_id());
            carModelDTO.setCarsLeasedOut(carsLeasedOut);

            int carsInRepair = carModelRepo.getCarsInRepairCountByModelId(carModel.getCar_model_id());
            carModelDTO.setCarsInRepair(carsInRepair);

            int carsInStock = carModelRepo.getCarCountByModelId(carModel.getCar_model_id());
            carsInStock = carsInStock - carsLeasedOut - carsInRepair;

            carModelDTO.setCarsInStock(carsInStock);

            carModelDTOList.add(carModelDTO);
        }
        return carModelDTOList;
    }


    public List<Car_Model> getCarModels() {
     return carModelRepo.getCarModels();
    }

    public List<Car_Model_Max_Km_Plan> getCarModelMaxKmPlans(int carModelId) {
        return carModelRepo.getCarModelMaxKmPlans(carModelId);
    }
    public List<Car_Model_Lease_Period_Plan> getCarModelLeasePeriodPlans(int carModelId) {
        return carModelRepo.getCarModelLeasePeriodPlans(carModelId);
    }

    // Insert methods
    public void addCarModel(String car_model){
        carModelRepo.addCarModel(car_model);
    }

    public void addNewCarModelMaxKmPlan(int car_model_id, int max_km, int km_price_per_month){
        carModelRepo.addCarModelMaxKmPlan(car_model_id, max_km, km_price_per_month);
    }
    public void addCarModelLeasePlan(int car_model_id, String type, int price_per_month, int nrOfMonths){
        carModelRepo.addCarModelLeasePlan(car_model_id, type, price_per_month, nrOfMonths);
    }

    // Key values
    public int maxModelId() {
       return carModelRepo.maxModelId();
    }


    // Get methods
    public Car_Model getCarModel(int car_model_id){
        return carModelRepo.getCarModel(car_model_id);
    }
}
