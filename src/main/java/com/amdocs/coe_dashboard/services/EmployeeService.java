package com.amdocs.coe_dashboard.services;

import com.amdocs.coe_dashboard.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {

    boolean employeeLogin(String email, String passwd);

    Employee registerEmployee(String id, Employee employee);

    List<Employee> getEmployeeDetails(String input);

    Employee getEmployeeDetailsById(String id);

    // Method to update employee details
    Employee updateEmployeeDetails(String employeeId, Employee updatedProfile);

}
