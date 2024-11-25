package com.amdocs.coe_dashboard.services;
import com.amdocs.coe_dashboard.authentication.AuthenticationEmp;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.repositories.EmployeeRepository;
import com.amdocs.coe_dashboard.repositories.EmployeeRepositoryImpl;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    public EmployeeRepository employeeRepository;

//
//    private static final String SECRET_KEY = "your_secret_key";  // Use a secure key here
//    private static final long EXPIRATION_TIME = 30_000L; // 10 days in milliseconds



    //==========================
    @Override
    public String employeeLogin(String email, String passwd)  {
        Optional<Employee> employeeOpt = employeeRepository.employeeLogin(email, passwd);


        if(employeeOpt.isEmpty())
            return "";

        // Generate JWT Token
        return new AuthenticationEmp().generateJwtToken(employeeOpt.get().getEmpEmail());
    }
//
//    private String generateJwtToken(Employee employee) {
//        return JWT.create()
//                .withSubject(employee.getEmpEmail()) // Store the email in the JWT
//                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration date
//                .withIssuer("your-app")  // You can replace with your app name
//                .sign(Algorithm.HMAC256(SECRET_KEY)); // Sign with a secret key
//    }
//
//    public DecodedJWT validateJwtToken(String token) throws JWTVerificationException {
//        try {
//            // Create JWT verifier using the SECRET_KEY and ISSUER to validate the token
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
//                    .withIssuer("your-app") // Ensure the token was issued by your app
//                    .build();  // Create the verifier
//
//            // Verify the token and return the decoded JWT
////            DecodedJWT d = verifier.verify(token);
//            return verifier.verify(token);
//
//        } catch (JWTVerificationException e) {
//            // If verification fails (invalid token, expired token, etc.), throw an exception
//            throw new JWTVerificationException("Invalid or expired token");
//        }
//    }

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
