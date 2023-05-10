package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.Car_Lease_Period_Plan;
import com.example.bilabonnement.model.Car_Max_Km_Plan;
import com.example.bilabonnement.model.Car_Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CarInfoDTORepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Car_Model> getCarModels() {
        String sql = "SELECT * FROM car_model";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car_Model.class));
    }

    public List<Car_Lease_Period_Plan> getCarLeasePeriodPlans(int carModelId) {
        String sql = "SELECT * FROM car_lease_period_plan WHERE car_model_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car_Lease_Period_Plan.class), carModelId);
    }

    public List<Car_Max_Km_Plan> getCarMaxKmPlans(int carModelId) {
        String sql = "SELECT * FROM car_max_km_plan WHERE car_model_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car_Max_Km_Plan.class), carModelId);
    }

    public int getCarsLeasedOut(int carModelId) {
        String sql = "SELECT COUNT(*) FROM contract INNER JOIN car ON contract.car_id = car.car_id INNER JOIN car_model ON car.car_model_id = car_model.car_model_id WHERE car_model.car_model_id = ? AND contract.end_date > NOW()";
        return jdbcTemplate.queryForObject(sql, Integer.class, carModelId);
    }

    public int getAllCarsCount(int carModelId) {
        String sql = "SELECT COUNT(*) FROM car WHERE car_model_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, carModelId);
    }

    public int getCarsInRepair(int carModelId) {
        String sql = "SELECT COUNT(*) FROM car_return_damage INNER JOIN car_return_report ON car_return_damage.car_return_report_id = car_return_report.car_return_report_id INNER JOIN car ON car_return_report.car_id = car.car_id INNER JOIN car_model ON car.car_model_id = car_model.car_model_id WHERE car_model.car_model_id = ? AND car_return_damage.isFixed = 0";
        return jdbcTemplate.queryForObject(sql, Integer.class, carModelId);
    }





}