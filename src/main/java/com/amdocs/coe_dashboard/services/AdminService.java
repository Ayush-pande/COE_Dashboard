package com.amdocs.coe_dashboard.services;

import com.amdocs.coe_dashboard.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {

    String adminLogin(String email, String passwd);

    Employee updateEmployeeDetails(String id, Employee updatedEmployeeDetails);

    List<Employee> getEmployeeDetails(String input);

    Employee getEmployeeDetailsById(String id);

    Employee addEmployee(String id, Employee employee);
}
