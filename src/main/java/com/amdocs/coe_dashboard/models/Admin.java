package com.amdocs.coe_dashboard.models;

public class Admin {
    private String adminEmail;
    private String adminPassword;
    private String adminName;

    public Admin() {
    }

    public Admin(String adminEmail, String adminPassword, String adminName) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
