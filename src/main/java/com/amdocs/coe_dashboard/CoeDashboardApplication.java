package com.amdocs.coe_dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.amdocs.coe_dashboard.authentication",
        "com.amdocs.coe_dashboard.config",
        "com.amdocs.coe_dashboard.controller",
        "com.amdocs.coe_dashboard.models",
        "com.amdocs.coe_dashboard.repositories",
        "com.amdocs.coe_dashboard.services"
})
public class CoeDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoeDashboardApplication.class, args);
    }

}
