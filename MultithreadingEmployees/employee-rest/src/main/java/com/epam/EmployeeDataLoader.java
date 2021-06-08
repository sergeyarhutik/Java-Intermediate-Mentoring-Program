package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataLoader implements ApplicationRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired SalaryRepository salaryRepository;

    @Override
    public void run(ApplicationArguments args) {
        employeeRepository.save(new Employee("Alexander", "Ivanov", 33));
        employeeRepository.save(new Employee("Vitaliy", "Petrov", 39));
        employeeRepository.save(new Employee("Evgeniy", "Sidorov", 27));
        employeeRepository.save(new Employee("Alexey", "Egorov", 24));
        employeeRepository.save(new Employee("Igor", "Alexandrov", 35));

        salaryRepository.save(new Salary(1L, 980));
        salaryRepository.save(new Salary(2L, 380));
        salaryRepository.save(new Salary(3L, 1600));
        salaryRepository.save(new Salary(4L, 2200));
        salaryRepository.save(new Salary(5L, 990));
    }

}