package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.ContractInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<ContractInfo> getAllContractInfo() {
        String sql = "SELECT \n" +
                "    c.contract_id,\n" +
                "    cu.customer_id,\n" +
                "    cu.customer_name,\n" +
                "    ca.car_id,\n" +
                "    cm.car_model_id,\n" +
                "    cm.car_model,\n" +
                "    ca.vognnummer,\n" +
                "    clp.car_lease_period_plan_id,\n" +
                "    clp.type AS lease_type,\n" +
                "    clp.price_per_month AS lease_price,\n" +
                "    ckp.car_max_km_plan_id,\n" +
                "    ckp.max_km,\n" +
                "    ckp.price_per_month AS km_plan_price,\n" +
                "    c.start_date,\n" +
                "    c.end_date,\n" +
                "    (clp.price_per_month + ckp.price_per_month) AS total_price_per_month\n" +
                "FROM\n" +
                "    contract c\n" +
                "JOIN\n" +
                "    customer cu ON c.customer_id = cu.customer_id\n" +
                "JOIN\n" +
                "    car ca ON c.car_id = ca.car_id\n" +
                "JOIN\n" +
                "    car_model cm ON ca.car_model_id = cm.car_model_id\n" +
                "JOIN\n" +
                "    car_lease_period_plan clp ON c.car_lease_period_plan_id = clp.car_lease_period_plan_id\n" +
                "JOIN\n" +
                "    car_max_km_plan ckp ON c.car_max_km_plan = ckp.car_max_km_plan_id;\n";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractInfo.class));
    }
}
