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

    // Lists
    public List<Customer> getAllCustomer(){
        return customerRepo.getAllCustomer();
    }

    // Insert methods
    public void createCustomer(String customer_name) {
        customerRepo.createCustomer(customer_name);
    }

    // Update methods
    public void editCustomer(String customer_name, int customer_id) {
        customerRepo.editCustomer(customer_name, customer_id);
    }

    // Get methods
    public Customer getCustomer(int customer_id){
        return customerRepo.getCustomer(customer_id);
    }
}
