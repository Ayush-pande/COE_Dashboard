package com.amdocs.coe_dashboard.repositories;

import com.amdocs.coe_dashboard.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AdminRepository {
    boolean adminLogin(String email, String passwd);

    List<Employee> findByIdOrName(String input);

    Employee addEmp(String id, Employee employee);

    Employee update(String id, Employee employee);

    Employee getEmpById(String id);

}
