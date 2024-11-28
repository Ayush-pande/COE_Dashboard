package com.amdocs.coe_dashboard.services;
import com.amdocs.coe_dashboard.authentication.AuthenticationEmp;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    AuthenticationEmp authenticationEmp;

    @Override
    public String employeeLogin(String email, String password)  {
        Optional<Employee> employeeOpt = employeeRepository.employeeLogin(email, password);


        if(employeeOpt.isEmpty())
            return "";

        // Generate JWT Token
        return authenticationEmp.generateJwtToken(employeeOpt.get().getEmpEmail());
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

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmp();
    }
}
