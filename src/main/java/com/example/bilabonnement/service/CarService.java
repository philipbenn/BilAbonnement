package com.example.bilabonnement.service;


import com.example.bilabonnement.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarService {

@Autowired
JdbcTemplate jdbcTemplate;

@Autowired
CarRepo carRepo;

    public void registerCar(int car_model_id, String vognnummer){
        carRepo.registerCar(car_model_id, vognnummer);
    }
}
