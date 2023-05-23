package com.example.bilabonnement.service;


import com.example.bilabonnement.model.car.Car;
import com.example.bilabonnement.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {



@Autowired
CarRepo carRepo;

    // Lists
    public List<Car> getAvailableCarsByCarModelId(Integer car_model_id) {
       return carRepo.getAvailableCarsByCarModelId(car_model_id);
    }

    public List <Car> getCarsByCarModelId(Integer car_model_id) {
        return carRepo.getCarsByCarModelId(car_model_id);
    }

    // Insert Methods
    public void registerCar(Integer car_model_id, String vognnummer){
        carRepo.registerCar(car_model_id, vognnummer);
    }
}
