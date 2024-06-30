package com.eCommerceApp.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.sql.SQLException;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ECommerceApplication.class, args);
		System.out.println("hello");
	}

}
