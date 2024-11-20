package com.amdocs.coe_dashboard.services;

import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.repositories.EmployeeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    public EmployeeRepositoryImpl employeeRepository;

    @Override
    public List<Employee> getEmployeeDetails(String input) {
        return employeeRepository.findByIdOrName(input);
    }

    @Override
    public Employee updateEmployeeDetails(String employeeId, Employee updatedProfile) {
        return employeeRepository.update(employeeId, updatedProfile);
    }
}
