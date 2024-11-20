package com.amdocs.coe_dashboard.controller;


import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<Employee> employeeLogin(@RequestBody Employee employee) {
        try {
            List<Employee> ls = employeeService.employeeLogin(employee.getEmpEmail(), employee.getEmpPasswd());
            if(!ls.isEmpty())
                return new ResponseEntity<>(ls.get(0), HttpStatus.OK);
            return new ResponseEntity<>(new Employee(), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmp/{input}")
    public ResponseEntity<List<Employee>> getEmployeeDetails(@PathVariable String input) {
        try {
            List<Employee> emp = employeeService.getEmployeeDetails(input);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable String id) {
        try {
            Employee emp = employeeService.getEmployeeDetailsById(id);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee){
        try {
            Employee emp = employeeService.registerEmployee(employee.getEmpId(), employee);
            return new ResponseEntity<>(emp, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployeeDetails(@RequestBody Employee employee){
        try {
            Employee emp = employeeService.updateEmployeeDetails(employee.getEmpId(),employee);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }


}