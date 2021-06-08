package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SalaryService")
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    SalaryRepository salaryRepository;
/*

    public SalaryServiceImpl(SalaryRepository SalaryRepository) {
        this.salaryRepository = salaryRepository;
    }
*/

    @Override
    public Salary getSalaryByEmployeeId(Long employeeId) {
        return salaryRepository.getOne(employeeId);
    }
}