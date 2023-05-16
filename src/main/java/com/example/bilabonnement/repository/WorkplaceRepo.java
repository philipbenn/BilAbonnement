package com.example.bilabonnement.repository;


import com.example.bilabonnement.model.Car_Return_DamageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkplaceRepo {

    @Autowired
    JdbcTemplate template;

    public List<Car_Return_DamageDTO> getAllDamageInfo() {
        String sql = "CALL get_damage_report";
        return template.query(sql,new BeanPropertyRowMapper<>(Car_Return_DamageDTO.class));
    }
}
