package com.example.bilabonnement.repository;

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
    public List<com.example.bilabonnement.model.ContractTypeCountDTO> contractTypeCountDTOS() {
        String sql = "SELECT clpp.type, COUNT(*) as count\n" +
                "FROM contract c\n" +
                "JOIN car_lease_period_plan clpp\n" +
                "ON c.car_lease_period_plan_id = clpp.car_lease_period_plan_id\n" +
                "GROUP BY clpp.type;\n";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(com.example.bilabonnement.model.ContractTypeCountDTO.class));
    }
}
