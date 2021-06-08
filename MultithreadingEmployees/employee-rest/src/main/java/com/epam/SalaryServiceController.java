package com.epam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Salary REST")
public class SalaryServiceController {

    @Autowired
    SalaryService salaryService;

    @GetMapping(value = "/salary/{employeeId}")
    @ApiOperation("Get Salary by employeeId")
    @ResponseStatus(value = HttpStatus.FOUND)
    public Salary getSalary(@PathVariable("employeeId") Long employeeId) {
        System.out.println("LOG: getSalary by employeeId " + employeeId);
        return salaryService.getSalaryByEmployeeId(employeeId);
    }
}