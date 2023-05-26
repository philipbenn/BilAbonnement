package com.example.bilabonnement.controller;

import com.example.bilabonnement.model.contract.Contract;
import com.example.bilabonnement.service.ContractService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContractControllerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    void addContract() {

        String sql = "SELECT * FROM contract";
        List<Contract> contractList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contract.class));
        Integer initialSize = contractList.size();
        String sqlInsert = "INSERT INTO contract () VALUES ()";
        jdbcTemplate.update(sqlInsert);
        contractList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contract.class));
        Integer newSize = contractList.size();

        assertNotEquals(initialSize, newSize);
    }
}