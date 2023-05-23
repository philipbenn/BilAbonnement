package com.example.bilabonnement.controller;

import com.example.bilabonnement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    // Views
    @GetMapping("/addCustomerForm")
    public String showCustomerForm() {
        return "customer/addCustomerForm";
    }
    @GetMapping("/customersOverview")
    public String addContract(Model model){
        model.addAttribute("getCustomers", customerService.getAllCustomer());
        return "customer/customersOverview";
    }
    @GetMapping("/editCustomerForm/{customer_id}")
    public String editCustomerForm(@PathVariable Integer customer_id, Model model) {
        model.addAttribute("customer_id", customer_id);
        model.addAttribute("customer_name", customerService.getCustomer(customer_id).getCustomer_name());
        return "customer/editCustomerForm";
    }


    // Post Methods
    @PostMapping("/editCustomer")
    public String editCustomer(@RequestParam Integer customer_id, @RequestParam String customer_name) {
        customerService.editCustomer(customer_name, customer_id);
        return "redirect:/customersOverview";
    }
    @PostMapping("/newCustomer")
    public String createCustomer(@RequestParam String customer_name) {
        customerService.createCustomer(customer_name);
        return "redirect:/customersOverview";
    }
}
