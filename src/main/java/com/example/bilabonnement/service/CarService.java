package com.example.bilabonnement.service;


import com.example.bilabonnement.model.car.Car;
import com.example.bilabonnement.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

@Autowired
JdbcTemplate jdbcTemplate;

@Autowired
CarRepo carRepo;

    public List<Car> getCarsByCarModelId(int car_model_id) {
       return carRepo.getCarsByCarModelId(car_model_id);
    }


    public void registerCar(int car_model_id, String vognnummer){
        carRepo.registerCar(car_model_id, vognnummer);
    }
}
