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

    // Registrerer en ny bil med en given model id og vognnummer i databasen.
    public void registerCar(int car_model_id, String vognnummer){
        String sql = "INSERT INTO car (car_model_id, vognnummer) VALUES (?, ?)";
        jdbcTemplate.update(sql, car_model_id, vognnummer);
    }

    // Henter en liste af tilgængelige biler for en given bilmodel,
    // der hverken er i reparation eller udlejet.
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
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class), car_model_id);
    }
}