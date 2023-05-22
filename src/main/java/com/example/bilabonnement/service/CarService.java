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
    public List<Car> getCarsByCarModelId(int car_model_id) {
       return carRepo.getAvailableCars(car_model_id);
    }

    // Insert Methods
    public void registerCar(int car_model_id, String vognnummer){
        carRepo.registerCar(car_model_id, vognnummer);
    }
}
