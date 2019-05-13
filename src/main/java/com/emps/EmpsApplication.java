package com.emps;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmpsApplication {

	public static void main(String[] args) throws ParseException {
		 SpringApplication.run(EmpsApplication.class, args);
		
		 System.out.println(new BCryptPasswordEncoder().encode("emps1410admin"));
		 System.out.println(new BCryptPasswordEncoder().encode("admin"));
			
	}

}
