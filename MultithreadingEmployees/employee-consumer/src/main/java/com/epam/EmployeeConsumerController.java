package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class EmployeeConsumerController {
    final String restUrl = "http://localhost:8080";

    @Autowired
    EmployeeConsumerService employeeConsumerService;

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeConsumerService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "allemployees";
    }

    @GetMapping("/employeesasync")
    public String getAllEmployeesAsync(Model model) throws InterruptedException, ExecutionException {
        System.out.println("/employeeasync controller runned, service invoked");
        List<Employee> employees = employeeConsumerService.getAllEmployeesAsync().get();
        model.addAttribute("employees", employees);
        return "allemployees";
    }

}