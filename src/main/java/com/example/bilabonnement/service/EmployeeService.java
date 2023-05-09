package com.example.bilabonnement.service;

import com.example.bilabonnement.model.Employee;
import com.example.bilabonnement.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    public Employee logIn(int employee_id, String password){
        return employeeRepo.logIn(employee_id, password);
    }

    public void addEmployee(Employee employee){
        employeeRepo.addEmployee(employee);
    }
}
