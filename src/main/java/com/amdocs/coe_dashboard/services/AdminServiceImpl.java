package com.amdocs.coe_dashboard.services;

import com.amdocs.coe_dashboard.authentication.Authentication;
import com.amdocs.coe_dashboard.models.Admin;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    Authentication authentication;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String to, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("server.email949@gmail.com");  // You can also specify the sender here
        message.setTo(to);  // Recipient's email
        message.setSubject("Register Request Approved");  // Email subject
        message.setText("Hello "+name+". Your register request was approved. Please login once to check");  // Email content

        // Send email
        javaMailSender.send(message);
    }

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

    @Override
    public List<Employee> getRegisterRequests() {
        return adminRepository.getRegisterRequests();
    }

    @Override
    public Employee approveRequest(Employee employee) {
        adminRepository.approveRequest(employee);
        sendSimpleEmail(employee.getEmpEmail(), employee.getEmpName());
        return employee;
    }
}
