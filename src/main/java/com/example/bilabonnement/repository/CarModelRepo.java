package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.carModel.Car_Model_Lease_Period_Plan;
import com.example.bilabonnement.model.carModel.Car_Model_Max_Km_Plan;
import com.example.bilabonnement.model.carModel.Car_Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarModelRepo {
    @Autowired
    JdbcTemplate template;

    // Lists
    public List<Car_Model> getCarModels() {
        String sql = "SELECT * FROM car_model";
        return template.query(sql, new BeanPropertyRowMapper<>(Car_Model.class));
    }
    public List<Car_Model_Max_Km_Plan> getCarModelMaxKmPlans(int carModelId) {
        String sql = "SELECT * FROM car_model_max_km_plan WHERE car_model_id = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Car_Model_Max_Km_Plan.class), carModelId);
    }
    public List<Car_Model_Lease_Period_Plan> getCarModelLeasePeriodPlans(int carModelId) {
        String sql = "SELECT * FROM car_model_lease_period_plan WHERE car_model_id = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Car_Model_Lease_Period_Plan.class), carModelId);
    }

    // Get Methods
    public Car_Model getCarModel(int car_model_id) {
        String sql = "SELECT * FROM car_model WHERE car_model_id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Car_Model.class), car_model_id);
    }

    // Key Values
    public int maxModelId() {
        String sql = "SELECT MAX(car_model_id) FROM car_model;";
        return template.queryForObject(sql, Integer.class);
    }
    public int getCarsLeasedOutCount(int carModelId) {
        String sql = "SELECT COUNT(*) FROM contract JOIN car ON contract.car_id = car.car_id INNER JOIN car_model ON car.car_model_id = car_model.car_model_id WHERE car_model.car_model_id = ? AND NOW() + INTERVAL 1 HOUR BETWEEN start_date AND end_date";
        return template.queryForObject(sql, Integer.class, carModelId);
    }
    public int getAllCarsCount(int carModelId) {
        String sql = "SELECT COUNT(*) FROM car WHERE car_model_id = ?";
        return template.queryForObject(sql, Integer.class, carModelId);
    }
    public int getCarsInRepairCount(int carModelId) {
        String sql = "SELECT COUNT(*) FROM car_return_damage INNER JOIN car_return_report ON car_return_damage.car_return_report_id = car_return_report.car_return_report_id INNER JOIN car ON car_return_report.car_id = car.car_id INNER JOIN car_model ON car.car_model_id = car_model.car_model_id WHERE car_model.car_model_id = ? AND car_return_damage.isFixed = 0";
        return template.queryForObject(sql, Integer.class, carModelId);
    }

    // Insert Methods
    public void addCarModelMaxKmPlan(int car_model_id, int max_km, int km_price_per_month) {
        String sql = "INSERT INTO car_model_max_km_plan (car_model_id, max_km, km_price_per_month) VALUES (?, ?, ?)";
        template.update(sql, car_model_id, max_km, km_price_per_month);
    }
    public void addCarModelLeasePlan(int car_model_id, String type, int price_per_month, int nrOfMonths) {
        String sql = "INSERT INTO car_model_lease_period_plan (car_model_id, type, price_per_month, nr_of_months)" +
                "VALUES (?, ?, ?, ?)";
        template.update(sql, car_model_id, type, price_per_month, nrOfMonths);
    }
    public void addCarModel(String car_model) {
        String sql = "INSERT INTO car_model (car_model_name) VALUES (?)";
        template.update(sql, car_model);
    }

}
