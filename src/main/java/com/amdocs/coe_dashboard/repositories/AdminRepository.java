package com.amdocs.coe_dashboard.repositories;

import com.amdocs.coe_dashboard.models.Admin;
import com.amdocs.coe_dashboard.models.Employee;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    Optional<Admin> adminLogin(String email, String passwd);

    List<Employee> findByIdOrName(String input);

    Employee addEmp(String id, Employee employee);

    Employee update(String id, Employee employee);

    Employee getEmpById(String id);

    List<Employee> getAllEmp();

    String delete(String id);

    List<Employee> getRegisterRequests();

    Employee approveRequest(Employee employee);

    Employee rejectRequest(Employee employee);

    List<String> getEmployeeDomainList();

    List<String> getEmployeeSkillsList();
}
