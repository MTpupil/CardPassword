package com.mtpupil;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mtpupil.mapper")
@SpringBootApplication
public class CardPasswordApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CardPasswordApplication.class, args);
	}
	
}
