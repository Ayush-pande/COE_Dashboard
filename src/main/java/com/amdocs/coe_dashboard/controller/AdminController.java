package com.amdocs.coe_dashboard.controller;

import com.amdocs.coe_dashboard.JwtUtil;
import com.amdocs.coe_dashboard.models.Admin;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@Slf4j
public class AdminController {

    private final String publicSecret = "abcd-efgh-ijkl";

    @Autowired
    AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

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
            if(adminService.adminLogin(admin.getAdminEmail(), admin.getAdminPassword())){
                System.out.println("Hello====================");
                return new ResponseEntity<>(jwtUtil.generateToken(publicSecret), HttpStatus.OK);
            }
            else{
                System.out.println("===Hello====================");
                return new ResponseEntity<>("Invalid",HttpStatus.OK);}
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmp/{input}")
    public ResponseEntity<List<Employee>> getEmployeeDetails(@RequestHeader(value = "Authorization", defaultValue = "") String authorizationToken, @PathVariable String input) {
        try {

            if(!jwtUtil.validateToken(authorizationToken , publicSecret)) return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);

            List<Employee> emp = adminService.getEmployeeDetails(input);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@RequestHeader(value = "Authorization", defaultValue = "") String authorizationToken,@PathVariable String id) {
        try {
            if(!jwtUtil.validateToken(authorizationToken , publicSecret)) return new ResponseEntity<>(new Employee(), HttpStatus.UNAUTHORIZED);
            Employee emp = adminService.getEmployeeDetailsById(id);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestHeader(value = "Authorization", defaultValue = "") String authorizationToken,@RequestBody Employee employee){
        try {
            if(!jwtUtil.validateToken(authorizationToken , publicSecret)) return new ResponseEntity<>(new Employee(), HttpStatus.UNAUTHORIZED);
            Employee emp = adminService.addEmployee(employee.getEmpId(), employee);
            return new ResponseEntity<>(emp, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployeeDetails(@RequestHeader(value = "Authorization", defaultValue = "") String authorizationToken,@RequestBody Employee employee){
        try {
            if(!jwtUtil.validateToken(authorizationToken , publicSecret)) return new ResponseEntity<>(new Employee(), HttpStatus.UNAUTHORIZED);
            Employee emp = adminService.updateEmployeeDetails(employee.getEmpId(),employee);
            return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
        }
    }

}
