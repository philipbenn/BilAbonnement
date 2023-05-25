package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void registerCar(Integer car_model_id, String vognnummer){
        String sql = "INSERT INTO car (car_model_id, vognnummer) VALUES (?, ?)";
        jdbcTemplate.update(sql, car_model_id, vognnummer);
    }
    public List<Car> getAvailableCarsByCarModelId(Integer car_model_id) {
        String sql =
            """
                SELECT * FROM car
                WHERE car_id NOT IN (SELECT car_id FROM contract) AND car_model_id = ?;
            """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class), car_model_id);
    }
    
    public List<Car> getCarsByCarModelId (Integer car_model_id) {
        String sql = "SELECT * FROM car WHERE car_model_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class), car_model_id);
    }
}