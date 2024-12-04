package com.amdocs.coe_dashboard.repositories;

import com.amdocs.coe_dashboard.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> employeeLogin(String email, String passwd);

    List<Employee> findByIdOrName(String name);

    Employee addEmp(String id, Employee employee);

    Employee update(String id, Employee employee);

    Employee getEmpById(String id);

    List<Employee> getAllEmp();

    List<String> getEmployeeDomainList();

    List<String> getEmployeeSkillsList();
}
