package com.amdocs.coe_dashboard.controller;


import com.amdocs.coe_dashboard.authentication.AuthenticationEmp;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.models.LoginResponse;
import com.amdocs.coe_dashboard.services.EmployeeService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    AuthenticationEmp authenticationEmp;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> employeeLogin(@RequestBody Employee employee) {
        try {
            // Authenticate user and get the JWT token
            String token = employeeService.employeeLogin(employee.getEmpEmail(), employee.getEmpPasswd());

            // Query the employee details again (in case you need the full employee object)
            List<Employee> employeeOpt = employeeService.getEmployeeDetails(employee.getEmpEmail());
            if (employeeOpt.isEmpty() || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(new Employee(),""));
            }

            // Create a LoginResponse containing both Employee and JWT token
            LoginResponse loginResponse = new LoginResponse(employeeOpt.get(0), token);
            return ResponseEntity.ok(loginResponse);  // Return both Employee and Token in response body
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse(new Employee(),""));
        }
    }

    @GetMapping("/getEmp/{input}")
    public ResponseEntity<List<Employee>> getEmployeeDetails(@RequestHeader(value = "Authorization") String token, @PathVariable String input) {
        try {
            authenticationEmp.validateJwtToken(token);

            List<Employee> emp = employeeService.getEmployeeDetails(input);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        }  catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAllEmp")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestHeader(value = "Authorization") String token) {
        try {
            authenticationEmp.validateJwtToken(token);

            List<Employee> emp = employeeService.getAllEmployees();
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        }  catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        try {
            authenticationEmp.validateJwtToken(token);
            Employee emp = employeeService.getEmployeeDetailsById(id);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        }  catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.UNAUTHORIZED);
        }  catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee( @RequestBody Employee employee){
        try {
            employeeService.registerEmployee(employee.getEmpId(), employee);
            String token = employeeService.employeeLogin(employee.getEmpEmail(), employee.getEmpPasswd());

            if(token.isEmpty()){
                //delete employee registered
                return new ResponseEntity<>("", HttpStatus.CREATED);
            }
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }  catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }  catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployeeDetails(@RequestHeader(value = "Authorization") String token, @RequestBody Employee employee){
        try {
            authenticationEmp.validateJwtToken(token);
            Employee emp = employeeService.updateEmployeeDetails(employee.getEmpId(),employee);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        }  catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.UNAUTHORIZED);
        }  catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }


}