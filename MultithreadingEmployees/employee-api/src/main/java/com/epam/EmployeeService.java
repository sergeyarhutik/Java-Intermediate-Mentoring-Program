package com.epam;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee newEmployee);
    Employee updateEmployee(Employee updatedEmployee);
    Employee getEmployee(Long EmployeeId);
    void deleteEmployee(Long EmployeeId);
    List<Employee> getAllEmployees();
}