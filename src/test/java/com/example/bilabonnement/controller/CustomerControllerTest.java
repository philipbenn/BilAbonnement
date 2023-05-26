package com.example.bilabonnement.controller;

import com.example.bilabonnement.model.customer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomerControllerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void getAllCustomers() {
        String sql = "SELECT * FROM customer";
        List<Customer> customerList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        Integer initialSize = customerList.size();

        String sqlInsert = "INSERT INTO customer (customer_name) VALUES ('JUnit')";
        jdbcTemplate.update(sqlInsert);
        customerList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        Integer newSize = customerList.size();

        assertNotEquals(initialSize, newSize);
    }
}