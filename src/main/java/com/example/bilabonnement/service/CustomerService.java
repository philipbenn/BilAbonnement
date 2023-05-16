package com.example.bilabonnement.service;

import com.example.bilabonnement.model.customer.Customer;
import com.example.bilabonnement.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public void createCustomer(String customer_name) {
        customerRepo.createCustomer(customer_name);
    }

    public List<Customer> getAllCustomer(){
        return customerRepo.getAllCustomer();
    }

    public void editCustomer(String customer_name, int customer_id) {
        customerRepo.editCustomer(customer_name, customer_id);
    }
}
