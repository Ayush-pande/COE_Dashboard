package com.amdocs.coe_dashboard.services;

import com.amdocs.coe_dashboard.authentication.Authentication;
import com.amdocs.coe_dashboard.models.Admin;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    Authentication authentication;

    @Override
    public String adminLogin(String email, String passwd) {
        Optional<Admin> adminOpt = adminRepository.adminLogin(email, passwd);

        if(adminOpt.isEmpty())
            return "";

        return authentication.generateJwtToken(adminOpt.get().getAdminEmail());
    }

    @Override
    public Employee updateEmployeeDetails(String id, Employee updatedEmployeeDetails) {
        return adminRepository.update(id, updatedEmployeeDetails);
    }

    @Override
    public List<Employee> getEmployeeDetails(String input) {
        return adminRepository.findByIdOrName(input);
    }

    @Override
    public Employee getEmployeeDetailsById(String id) {
        return adminRepository.getEmpById(id);
    }

    @Override
    public Employee addEmployee(String id, Employee employee) {
        return adminRepository.addEmp(id, employee);
    }

    public List<Employee> getAllEmployees() {
        return adminRepository.getAllEmp();
    }

    @Override
    public String deleteEmployee(String id) {
        return adminRepository.delete(id);
    }
}
