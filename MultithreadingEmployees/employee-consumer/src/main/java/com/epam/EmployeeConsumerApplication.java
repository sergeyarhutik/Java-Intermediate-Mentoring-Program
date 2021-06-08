package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class EmployeeConsumerApplication implements CommandLineRunner {


    @Autowired
    EmployeeConsumerService employeeConsumerService;

    @Autowired
    SalaryConsumerService salaryConsumerService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeConsumerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(12);
        executor.setMaxPoolSize(12);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("EmployeeAsync-");
        executor.initialize();
        return executor;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n\n ----- START -----");
        System.out.println("1. Press Enter to call start calling async services");
        scan.nextLine();

        // Get Employees
        CompletableFuture<List<Employee>> employees = employeeConsumerService.getAllEmployeesAsync();
        System.out.println("EmployeeService -> getAllEmployeesAsync() called with CompletableFuture result, employees still null:\n" + employees);

        // Get Salaries
        List<CompletableFuture<Salary>> salariesList = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            salariesList.add(salaryConsumerService.getSalaryByEmployeeId((long) i));
        }
        System.out.println("SalaryService -> getSalaryByEmployeeId(id) called with CompletableFuture result," +
                " salariesList is still null:\n" + salariesList);
        System.out.println("----- ALL COMPLETABLEFUTURE CALLS COMPLETED -----");

        // Combine all CF's in one CF
        List<CompletableFuture<?>> combinedList = new ArrayList<>();
        combinedList.add(employees);
        combinedList.addAll(salariesList);
        CompletableFuture<Void> combinedFutures = CompletableFuture.allOf(combinedList.toArray(new CompletableFuture[combinedList.size()]));

        // Get final results
        System.out.println("2. After results is received press Enter again to show results.");
        scan.nextLine();
        System.out.println("combinedFutures.join() = " + combinedFutures.join());       // TODO: decombine correctly FUTUREs
/*        System.out.println("##### Results #####\nemployees = " + employees.get());
        System.out.print("salariesList = ");
        for (CompletableFuture<Salary> sal : salariesList) {
            System.out.print(sal.get() + ", ");
        }*/

        System.out.println("END OF RUN");
    }
}