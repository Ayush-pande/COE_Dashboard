/package com.amdocs.coe_dashboard.services;

import com.amdocs.coe_dashboard.models.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployeeDetails(String input);

    // Method to update employee details
    Employee updateEmployeeDetails(String employeeId, Employee updatedProfile);

}
