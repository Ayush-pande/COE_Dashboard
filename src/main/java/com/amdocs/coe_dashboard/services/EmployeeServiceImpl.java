package com.amdocs.coe_dashboard.services;

import com.amdocs.coe_dashboard.authentication.AuthForgotPassword;
import com.amdocs.coe_dashboard.authentication.AuthenticationEmp;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    AuthenticationEmp authenticationEmp;
    @Autowired
    AuthForgotPassword authForgotPassword;

    private final String FORGOT_PASSWORD_URL = "http://localhost:8080/reset?token=";

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("server.email949@gmail.com");  // You can also specify the sender here
        message.setTo(to);  // Recipient's email
        message.setSubject(subject);  // Email subject
        message.setText(text);  // Email content

        // Send email
        javaMailSender.send(message);
    }

    @Override
    public String employeeLogin(String email, String password) {
        Optional<Employee> employeeOpt = employeeRepository.employeeLogin(email, password);


        if (employeeOpt.isEmpty())
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

    @Override
    public Boolean forgotPassword(String email) {
        if (employeeRepository.findByIdOrName(email).isEmpty()) {
            return false;
        }
        String token = authForgotPassword.generateJwtToken(email);
        sendSimpleEmail(email, "Password reset", "Click on the link to reset: " + FORGOT_PASSWORD_URL + token);
        return true;

    }

    @Override
    public Boolean resetPassword(String email, String newPassword) {
        Optional<Employee> emp = employeeRepository.findByIdOrName(email).stream().findFirst();
        if (emp.isEmpty()) return false;
        emp.get().setEmpPassword(newPassword);
        employeeRepository.update(emp.get().getEmpId(), emp.get());

        return true;
    }

    @Override
    public List<String> getEmployeeDomainList() {
        return employeeRepository.getEmployeeDomainList();
    }

    @Override
    public List<String> getEmployeeSkillsList() {
        return employeeRepository.getEmployeeSkillsList();
    }
}
