package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractRepo {

    @Autowired
    JdbcTemplate template;


    public List<ContractInfo> getAllContractInfo() {
        //Find sql query under stored procedures
        String sql = "CALL get_all_contract_info";
        return template.query(sql, new BeanPropertyRowMapper<>(ContractInfo.class));
    }

    public List<Car_Model> getAllCarModels() {
        String sql = "SELECT * FROM car_model";
        return template.query(sql, new BeanPropertyRowMapper<>(Car_Model.class));
    }

    public List<Car_Lease_Period_Plan> getAllCarLeasePeriodPlansForCarModel(int carModelId) {
        String sql = "SELECT * FROM car_lease_period_plan WHERE car_model_id = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Car_Lease_Period_Plan.class), carModelId);
    }

    public List <Car_Max_Km_Plan> getAllCarMaxKmPlans(int carModelId) {
        String sql = "SELECT * FROM car_max_km_plan WHERE car_model_id = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Car_Max_Km_Plan.class), carModelId);
    }

    public void addContract(int car_id, int customer_id, int car_lease_period_plan_id,
                            int car_max_km_plan, String start_date, int employee_id){
        String sql = "INSERT INTO contract (car_id, customer_id, car_lease_period_plan_id, car_max_km_plan, start_date, employee_id, end_date) VALUES (?, ?, ?, ?, ?, ?, '2001-01-01')";
        template.update(sql, car_id, customer_id, car_lease_period_plan_id, car_max_km_plan, start_date, employee_id);
        updateEndDate();
    }

    public int getNewestContractId(){
        String sql = "SELECT MAX(contract_id) FROM contract";
        return template.queryForObject(sql, Integer.class);
    }
    public void updateEndDate(){
        String sql = "UPDATE contract c\n" +
                "JOIN car_lease_period_plan clp ON clp.car_lease_period_plan_id = c.car_lease_period_plan_id\n" +
                "SET c.end_date = DATE_ADD(c.start_date, INTERVAL clp.nr_of_months MONTH)\n" +
                "WHERE c.contract_id = ?;";
        template.update(sql, getNewestContractId());
    }

}
