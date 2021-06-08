package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;

@Service
public class SalaryConsumerService {

    @Value("${rest.url}")
    private String restUrl;

    @Autowired
    RestTemplate restTemplate;

    @Async
    public CompletableFuture<Salary> getSalaryByEmployeeId(Long employeeId) throws InterruptedException {
        System.out.println("SalaryService: Fetching ASYNCHRONOUSLY data from REST: " + restUrl + "/salary/" + employeeId);
        Salary salary = restTemplate.getForEntity (restUrl + "/salary/" + employeeId, Salary.class).getBody();
        Thread.sleep(1500L);
        System.out.println("SalaryService: Salary for Employee #" + employeeId + " received");
        return CompletableFuture.completedFuture(salary);
    }
}