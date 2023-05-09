package com.example.bilabonathome2.repository;

import com.example.bilabonathome2.model.Car_Lease_Period_Plan;
import com.example.bilabonathome2.model.Car_Max_Km_Plan;
import com.example.bilabonathome2.model.Car_Model;
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
        String carModelQuery = "SELECT * FROM car_model";
        return jdbcTemplate.query(carModelQuery, new BeanPropertyRowMapper<>(Car_Model.class));
    }

    public List<Car_Lease_Period_Plan> getCarLeasePeriodPlans(int carModelId) {
        String leasePeriodPlanQuery = "SELECT * FROM car_lease_period_plan WHERE car_model_id = ?";
        return jdbcTemplate.query(leasePeriodPlanQuery, new BeanPropertyRowMapper<>(Car_Lease_Period_Plan.class), carModelId);
    }

    public List<Car_Max_Km_Plan> getCarMaxKmPlans(int carModelId) {
        String maxKmPlanQuery = "SELECT * FROM car_max_km_plan WHERE car_model_id = ?";
        return jdbcTemplate.query(maxKmPlanQuery, new BeanPropertyRowMapper<>(Car_Max_Km_Plan.class), carModelId);
    }

    public int getCarsLeasedOut(int carModelId) {
        String leasedOutQuery = "SELECT COUNT(*) FROM contract INNER JOIN car ON contract.car_id = car.car_id INNER JOIN car_model ON car.car_model_id = car_model.car_model_id WHERE car_model.car_model_id = ? AND contract.end_date > NOW()";
        return jdbcTemplate.queryForObject(leasedOutQuery, Integer.class, carModelId);
    }

    public int getCarsInStock(int carModelId) {
        String leasedOutQuery = "SELECT COUNT(*) FROM car WHERE car_model_id = ?";
        return jdbcTemplate.queryForObject(leasedOutQuery, Integer.class, carModelId);
    }

    public int getCarsInRepair(int carModelId) {
        String inRepairQuery = "SELECT COUNT(*) FROM car_return_damage INNER JOIN car_return_report ON car_return_damage.car_return_report_id = car_return_report.car_return_report_id INNER JOIN car ON car_return_report.car_id = car.car_id INNER JOIN car_model ON car.car_model_id = car_model.car_model_id WHERE car_model.car_model_id = ? AND car_return_damage.isFixed = 0";
        return jdbcTemplate.queryForObject(inRepairQuery, Integer.class, carModelId);
    }





}
