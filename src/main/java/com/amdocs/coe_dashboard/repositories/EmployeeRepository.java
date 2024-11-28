package com.amdocs.coe_dashboard.repositories;

import com.amdocs.coe_dashboard.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository {

    Optional<Employee> employeeLogin(String email, String passwd);

    Page<Employee> findByIdOrName(String name);

    Employee addEmp(String id, Employee employee);

    Employee update(String id, Employee employee);

    Employee getEmpById(String id);
    List<Employee> getAllEmp();
}
