package com.amdocs.coe_dashboard.services;

import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.repositories.EmployeeRepository;
import com.amdocs.coe_dashboard.repositories.EmployeeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    public boolean employeeLogin(String email, String passwd) {
        return employeeRepository.employeeLogin(email, passwd);
    }

    @Override
    public Employee registerEmployee(String id, Employee employee) {
        return employeeRepository.addEmp(id, employee);
    }

    @Override
    public List<Employee> getEmployeeDetails(String input) {
        return employeeRepository.findByIdOrName(input);
    }

    @Override
    public Employee getEmployeeDetailsById(String id) {
        return employeeRepository.getEmpById(id);
    }

    @Override
    public Employee updateEmployeeDetails(String employeeId, Employee updatedProfile) {
        return employeeRepository.update(employeeId, updatedProfile);
    }
}
