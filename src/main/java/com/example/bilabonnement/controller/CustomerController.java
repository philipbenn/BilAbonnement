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

    @GetMapping("/createcustomerform")
    public String showCustomerForm() {
        return "newcustomer";
    }

    @PostMapping("/newcustomer")
    public String createCustomer(@RequestParam String customer_name) {
        customerService.createCustomer(customer_name);
        return "redirect:/customeroverview";
    }

    @GetMapping("/customeroverview")
    public String addContract(Model model){
        model.addAttribute("getCustomers", customerService.getAllCustomer());
        return "customeroverview";
    }

    @GetMapping("/editcustomerform/{customer_id}")
    public String editCustomerForm(@PathVariable int customer_id, Model model) {
        model.addAttribute("customer_id",customer_id);
        return "editcustomerform";
    }

    @PostMapping("/editcustomer")
    public String editCustomer(@RequestParam int customer_id, @RequestParam String customer_name) {
        customerService.editCustomer(customer_name, customer_id);
        return "redirect:/customeroverview";
    }


}
