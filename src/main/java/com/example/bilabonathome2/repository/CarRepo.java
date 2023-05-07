package com.example.bilabonathome2.repository;

import com.example.bilabonathome2.model.CarModelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<CarModelInfo> getAllCarInfo() {
     String sql = "SELECT \n" +
             "    cm.car_model_id,\n" +
             "    cm.car_model,\n" +
             "    COUNT(ca.car_id) AS quantity\n" +
             "FROM\n" +
             "    car_model cm\n" +
             "JOIN\n" +
             "    car ca ON cm.car_model_id = ca.car_model_id\n" +
             "GROUP BY\n" +
             "    cm.car_model_id, cm.car_model\n" +
             "ORDER BY\n" +
             "    cm.car_model_id;\n";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CarModelInfo.class));
    }

}
