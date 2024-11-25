package com.amdocs.coe_dashboard.controller;

import com.amdocs.coe_dashboard.authentication.Authentication;
import com.amdocs.coe_dashboard.authentication.AuthenticationEmp;
import com.amdocs.coe_dashboard.models.Admin;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.models.LoginResponse;
import com.amdocs.coe_dashboard.services.AdminService;
import com.auth0.jwt.exceptions.JWTVerificationException;
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
            String token = adminService.adminLogin(admin.getAdminEmail(), admin.getAdminPassword());

//            List<Admin> adminOpt = adminService.getEmployeeDetails()
            if(token.isEmpty())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(token);

            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>("invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmp/{input}")
    public ResponseEntity<List<Employee>> getEmployeeDetails(@RequestHeader(value = "Authorization") String token, @PathVariable String input) {
        try {
            new Authentication().validateJwtToken(token);
            List<Employee> emp = adminService.getEmployeeDetails(input);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        }  catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        try {
            new Authentication().validateJwtToken(token);
            Employee emp = adminService.getEmployeeDetailsById(id);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.UNAUTHORIZED);
        }  catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestHeader(value = "Authorization") String token, @RequestBody Employee employee){
        try {
            new Authentication().validateJwtToken(token);
            Employee emp = adminService.addEmployee(employee.getEmpId(), employee);
            return new ResponseEntity<>(emp, HttpStatus.CREATED);
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployeeDetails(@RequestHeader(value = "Authorization") String token, @RequestBody Employee employee){
        try {
            new Authentication().validateJwtToken(token);
            Employee emp = adminService.updateEmployeeDetails(employee.getEmpId(),employee);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllEmp")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestHeader(value = "Authorization") String token) {
        try {
            new Authentication().validateJwtToken(token);

            List<Employee> emp = adminService.getAllEmployees();
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        }  catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }


}
