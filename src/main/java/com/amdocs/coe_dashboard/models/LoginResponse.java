package com.amdocs.coe_dashboard.models;

public class LoginResponse {
    private Employee employee;
    private String token;

    public LoginResponse(Employee employee, String token) {
        this.employee = employee;
        this.token = token;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
