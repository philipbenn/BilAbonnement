package com.example.bilabonnement.controller;

import com.example.bilabonnement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/addCustomerForm")
    public String showCustomerForm() {
        return "customer/addCustomerForm";
    }

    @PostMapping("/newCustomer")
    public String createCustomer(@RequestParam String customer_name) {
        customerService.createCustomer(customer_name);
        return "redirect:/customersOverview";
    }

    @GetMapping("/customersOverview")
    public String addContract(Model model){
        model.addAttribute("getCustomers", customerService.getAllCustomer());
        return "customer/customersOverview";
    }

    @GetMapping("/editCustomerForm/{customer_id}")
    public String editCustomerForm(@PathVariable int customer_id, Model model) {
        model.addAttribute("customer_id",customer_id);
        return "customer/editCustomerForm";
    }

    @PostMapping("/editCustomer")
    public String editCustomer(@RequestParam int customer_id, @RequestParam String customer_name) {
        customerService.editCustomer(customer_name, customer_id);
        return "redirect:/customersOverview";
    }




}
