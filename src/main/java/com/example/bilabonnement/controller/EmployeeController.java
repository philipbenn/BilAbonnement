package com.example.bilabonnement.controller;

import com.example.bilabonnement.model.Employee;
import com.example.bilabonnement.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/loginForm")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/signUpForm")
    public String showSignUpForm() {
        return "signup";
    }

    @PostMapping("/login")
    public String logIn(@RequestParam int employee_id, @RequestParam String password, HttpSession session, Model model) {
        Employee employee = employeeService.logIn(employee_id, password);
        if (employee != null) {
            session.setAttribute("employee", employee);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid id or password");
            return "login";
        }
    }

    @PostMapping("/signupcustomer")
    public String customerSignUp(@ModelAttribute Employee employee){
        employeeService.addEmployee(employee);
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "startpage";
    }
}
