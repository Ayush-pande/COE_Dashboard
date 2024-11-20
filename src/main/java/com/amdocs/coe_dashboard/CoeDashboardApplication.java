package com.amdocs.coe_dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.amdocs.coe_dashboard.authentication")
public class CoeDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoeDashboardApplication.class, args);
	}

}
