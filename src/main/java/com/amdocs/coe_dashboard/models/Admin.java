package com.amdocs.coe_dashboard.models;

public class Admin {
    private String adminEmail;
    private String adminPassword;

    public Admin() {
    }

    public Admin(String adminEmail, String adminPassword) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminEmail(String adminEmail){
        this.adminEmail=adminEmail;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
