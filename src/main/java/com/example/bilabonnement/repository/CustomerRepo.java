package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate template;

    public void createCustomer(String customer_name) {
        String sql = "INSERT INTO customer (customer_name) VALUES (?)";
        template.update(sql, customer_name);
    }

    public List<Customer> getAllCustomer(){
        String sql = "SELECT * FROM customer";
        return template.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    public void editCustomer(String customer_name, int customer_id) {
        String sql = "UPDATE customer SET customer_name = ? WHERE customer_id = ?";
        template.update(sql, customer_name, customer_id);
    }
    public Customer getCustomer(int customer_id){
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = template.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), customer_id);
        return customer;
    }

}
