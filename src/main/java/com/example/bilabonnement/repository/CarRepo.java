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


    public void registerCar(int car_model_id, String vognnummer){
        String sql = "INSERT INTO car (car_model_id, vognnummer) VALUES (?, ?)";
        jdbcTemplate.update(sql, car_model_id, vognnummer);
    }
    public List<Car> getCarsByCarModelId(int car_model_id) {
        String sql = "SELECT car.car_id, car.car_model_id, car.vognnummer\n" +
                "FROM car\n" +
                "WHERE car.car_model_id = ? " +
                "  AND car.car_id NOT IN (\n" +
                "    SELECT contract.car_id\n" +
                "    FROM contract\n" +
                "    WHERE contract.end_date >= CURDATE()\n" +
                "  )\n";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class), car_model_id);
    }

}
