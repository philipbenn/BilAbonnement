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
    JdbcTemplate template;
    public void registerCar(int car_model_id, String vognnummer){
        String sql = "INSERT INTO car (car_model_id, vognnummer) VALUES (?, ?)";
        template.update(sql, car_model_id, vognnummer);
    }
    public List<Car> getAvailableCars(int car_model_id) {
        String sql =
            """
            SELECT car.car_id, car.car_model_id, car.vognnummer
            FROM car
            WHERE car.car_model_id = ? AND NOT EXISTS (
                SELECT 1
                FROM car_return_damage
                JOIN car_return_report
                ON car_return_damage.car_return_report_id = car_return_report.car_return_report_id
                WHERE car_return_report.car_id = car.car_id
                AND car_return_damage.isFixed = 0
            ) AND NOT EXISTS (
                SELECT 1
                FROM contract
                WHERE contract.car_id = car.car_id
                AND NOW() + INTERVAL 1 HOUR BETWEEN contract.start_date AND contract.end_date
            );
            """;
        return template.query(sql, new BeanPropertyRowMapper<>(Car.class), car_model_id);
    }
}