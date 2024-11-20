package com.amdocs.coe_dashboard.controller;

import com.amdocs.coe_dashboard.models.Admin;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/admin")
@Slf4j
public class AdminController {

    @Autowired
    AdminService adminService;

//    public AdminController(AdminService adminService) {
//        this.adminService = adminService;
//    }

    @GetMapping("/")
    public ResponseEntity<String> getTest(){
        return new ResponseEntity<>("Working", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody Admin admin) {
        try {
            if(adminService.adminLogin(admin.getAdminEmail(), admin.getAdminPassword()))
                return new ResponseEntity<>("true", HttpStatus.OK);
            return new ResponseEntity<>("false", HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>("invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmp/{input}")
    public ResponseEntity<List<Employee>> getEmployeeDetails(@PathVariable String input) {
        try {
            List<Employee> emp = adminService.getEmployeeDetails(input);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable String id) {
        try {
            Employee emp = adminService.getEmployeeDetailsById(id);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        try {
            Employee emp = adminService.addEmployee(employee.getEmpId(), employee);
            return new ResponseEntity<>(emp, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployeeDetails(@RequestBody Employee employee){
        try {
            Employee emp = adminService.updateEmployeeDetails(employee.getEmpId(),employee);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

}
