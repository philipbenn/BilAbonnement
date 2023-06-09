package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepo {
    @Autowired
    JdbcTemplate template;

    public Employee logIn(Integer employee_id, String password) {
        String sql = "SELECT * FROM employee WHERE employee_id = ? AND password = ?";
        try {
            RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
            return template.queryForObject(sql,rowMapper, employee_id, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void addEmployee(Employee employee){
        String sql = "INSERT INTO employee (name, password) VALUES (?, ?)";
        template.update(sql, employee.getName(), employee.getPassword());
    }

}
