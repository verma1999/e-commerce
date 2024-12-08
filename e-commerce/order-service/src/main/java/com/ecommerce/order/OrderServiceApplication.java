package com.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		System.out.println("start");
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
