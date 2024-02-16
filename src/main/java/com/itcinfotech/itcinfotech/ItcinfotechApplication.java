package com.itcinfotech.itcinfotech;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ItcinfotechApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItcinfotechApplication.class, args);
	}
}
