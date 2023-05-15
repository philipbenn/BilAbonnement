package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.ContractInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractTypeCountDTORepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public ContractTypeCountDTORepo() {
    }

    public List<ContractInfo> expiringContracts(){

        String sql = "SELECT \n" +
                "    car.vognnummer, \n" +
                "    car_model.car_model, \n" +
                "    customer.customer_name, \n" +
                "    (car_lease_period_plan.price_per_month + car_max_km_plan.km_price_per_month) AS total_price_per_month, \n" +
                "    contract_id,\n" +
                "    end_date\n" +
                "FROM \n" +
                "    contract\n" +
                "JOIN \n" +
                "    car ON contract.car_id = car.car_id\n" +
                "JOIN \n" +
                "    car_model ON car.car_model_id = car_model.car_model_id\n" +
                "JOIN \n" +
                "    customer ON contract.customer_id = customer.customer_id\n" +
                "JOIN \n" +
                "    car_lease_period_plan ON contract.car_lease_period_plan_id = car_lease_period_plan.car_lease_period_plan_id\n" +
                "JOIN \n" +
                "    car_max_km_plan ON contract.car_max_km_plan = car_max_km_plan.car_max_km_plan_id\n" +
                "WHERE \n" +
                "    contract.start_date >= CURDATE() AND contract.end_date > CURDATE()\n" +
                "ORDER BY \n" +
                "    contract.end_date ASC;\n";

         return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractInfo.class));
    }

    public int countAllCars(){
        String sql = "SELECT COUNT(*) FROM car";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int activeContracts(){
        String sql = "SELECT COUNT(*)\n" +
                "FROM contract\n" +
                "WHERE CURDATE() BETWEEN start_date AND end_date;\n";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
public int nrOfCarsInRepair(){
        String sql="SELECT COUNT(DISTINCT car_return_report_id) AS 'Number of Cars in Repair'\n" +
                "FROM car_return_damage\n" +
                "WHERE isFixed=0;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
}
    public double monthlyIncome(){
        String sql = "SELECT SUM(cpp.price_per_month + cmkp.km_price_per_month) AS total_monthly_income\n" +
                "FROM contract AS c\n" +
                "JOIN car_lease_period_plan AS cpp ON c.car_lease_period_plan_id = cpp.car_lease_period_plan_id\n" +
                "JOIN car_max_km_plan AS cmkp ON c.car_max_km_plan = cmkp.car_max_km_plan_id\n" +
                "WHERE c.start_date <= '2023-05-12' AND (c.end_date > '2023-05-12' OR c.end_date IS NULL)\n";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public List<com.example.bilabonnement.model.ContractTypeCountDTO> contractTypeCountDTOS() {
        String sql = "SELECT clpp.type, COUNT(*) as count\n" +
                "FROM contract c\n" +
                "JOIN car_lease_period_plan clpp\n" +
                "ON c.car_lease_period_plan_id = clpp.car_lease_period_plan_id\n" +
                "GROUP BY clpp.type;\n";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(com.example.bilabonnement.model.ContractTypeCountDTO.class));
    }
}
