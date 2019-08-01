package com.obss.movietracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude= {SecurityAutoConfiguration.class})
public class MovieTrackerApp {

	public static void main(String[] args) {
		SpringApplication.run(MovieTrackerApp.class, args);
	}
	
}
