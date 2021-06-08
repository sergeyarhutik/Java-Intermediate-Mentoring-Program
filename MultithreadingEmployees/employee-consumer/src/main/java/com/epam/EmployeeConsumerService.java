package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeConsumerService {

    @Value("${rest.url}")
    private String restUrl;

    @Autowired
    RestTemplate restTemplate;

    public List<Employee> getAllEmployees() {
        System.out.println("Fetching data from REST: " + restUrl);
        List<Employee> employees = (List<Employee>) restTemplate.getForEntity (restUrl + "/employees", List.class).getBody();
        //System.out.println("employees = " + employees.toString());
        return employees;
    }

    @Async
    public CompletableFuture<List<Employee>> getAllEmployeesAsync() throws InterruptedException {
        System.out.println("Employee service: Fetching ASYNCHRONOUSLY data from REST: " + restUrl + "/employees");
        List<Employee> employees = restTemplate.getForEntity (restUrl + "/employees", List.class).getBody();
        Thread.sleep(1500L);
        System.out.println("Employee service: Employees from REST received");
        return CompletableFuture.completedFuture(employees);
    }

}