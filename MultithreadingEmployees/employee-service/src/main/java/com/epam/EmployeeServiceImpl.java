package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository EmployeeRepository;
/*
    public EmployeeServiceImpl(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }*/

    @Override
    public Employee createEmployee(Employee newEmployee) {
        return EmployeeRepository.save(newEmployee);
    }

    @Override
    public Employee updateEmployee(Employee updatedEmployee) {
        if(EmployeeRepository.existsById(updatedEmployee.getId())) {
            return EmployeeRepository.save(updatedEmployee);
        }
        else {
            return null;
        }
    }

    @Override
    public Employee getEmployee(Long EmployeeId) {
        return EmployeeRepository.findById(EmployeeId)
                .orElseThrow(() -> new RuntimeException("Failed to get Employee by id: " + EmployeeId));
    }

    @Override
    public void deleteEmployee(Long EmployeeId) {
        EmployeeRepository.deleteById(EmployeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return EmployeeRepository.findAll();
    }

}