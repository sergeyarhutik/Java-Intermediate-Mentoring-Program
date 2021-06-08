package com.epam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "Employee REST")
public class EmployeeServiceController {

    @Autowired
    private EmployeeService EmployeeService;

    @GetMapping(value = "/")
    @ApiOperation("Redirects to swagger-ui")
    @ResponseStatus(value = HttpStatus.OK)
    public void getApi(HttpServletResponse response) throws IOException {
        System.out.println("LOG: getApi -> SWAGGER-UI ");
        response.sendRedirect("/swagger-ui/");
    }

    @PostMapping(value = "/employees")
    @ApiOperation("Create new Employee")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createEmployee(@RequestBody Employee newEmployee) {
        System.out.println("LOG: addEmployee: " + newEmployee);
        EmployeeService.createEmployee(newEmployee);
    }

    @PutMapping(value = "/employees")
    @ApiOperation("Update Employee by id")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateEmployee(@RequestBody Employee newEmployee) {
        System.out.println("LOG: updateEmployee: " + newEmployee);
        EmployeeService.updateEmployee(newEmployee);
    }

    @GetMapping(value = "/employees/{employeeId}")
    @ApiOperation("Get Employee by id")
    @ResponseStatus(value = HttpStatus.FOUND)
    public Employee getEmployee(@PathVariable("employeeId") Long employeeId) {
        System.out.println("LOG: getEmployee by id: " + employeeId);
        return EmployeeService.getEmployee(employeeId);
    }

    @DeleteMapping(value = "/employees/{employeeId}")
    @ApiOperation("Delete Employee by id")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        System.out.println("LOG: deleteEmployee by id: " + employeeId);
        EmployeeService.deleteEmployee(employeeId);
    }

    @GetMapping(value = "/employees")
    @ApiOperation("Get all Employees")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        System.out.println("LOG: getAllEmployees()");
        return EmployeeService.getAllEmployees();
    }

}