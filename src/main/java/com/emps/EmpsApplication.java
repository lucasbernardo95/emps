package com.emps;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.emps.repository.ClienteRepository;
import com.emps.repository.ProdutoRepository;

@SpringBootApplication
public class EmpsApplication {
	
	@Autowired
	private static ClienteRepository cr;
	
	@Autowired
	private static ProdutoRepository pr;

	public static void main(String[] args) throws ParseException {
		 SpringApplication.run(EmpsApplication.class, args);
		
		 System.out.println(new BCryptPasswordEncoder().encode("emps1410admin"));
		
	}

}
