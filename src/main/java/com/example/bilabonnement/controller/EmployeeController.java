package com.example.bilabonnement.controller;

import com.example.bilabonnement.model.employee.Employee;
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

    // Views
    @GetMapping("/loginForm")
    public String showLoginForm() {
        return "employee/loginForm";
    }
    @GetMapping("/signUpForm")
    public String showSignUpForm() {
        return "employee/signUpForm";
    }


    // Post Methods
    @PostMapping("/login")
    public String logIn(@RequestParam Integer employee_id, @RequestParam String password, HttpSession session, Model model) {
        Employee employee = employeeService.logIn(employee_id, password);
        if (employee != null) {
            session.setAttribute("employee", employee);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid id or password");
            return "employee/loginForm";
        }
    }
    @PostMapping("/signUpEmployee")
    public String customerSignUp(@ModelAttribute Employee employee){
        employeeService.addEmployee(employee);
        return "redirect:/dashboard";
    }



    // Invaliderer session og returnerer til startside
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "startPage/startPage";
    }
}
