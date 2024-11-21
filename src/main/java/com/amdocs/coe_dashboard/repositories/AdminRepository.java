package com.amdocs.coe_dashboard.repositories;

import com.amdocs.coe_dashboard.models.Admin;
import com.amdocs.coe_dashboard.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    Optional<Admin> adminLogin(String email, String passwd);

    List<Employee> findByIdOrName(String input);

    Employee addEmp(String id, Employee employee);

    Employee update(String id, Employee employee);

    Employee getEmpById(String id);

}
