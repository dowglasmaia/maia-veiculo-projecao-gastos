package com.maia.apiprojecaogastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiProjecaoGastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProjecaoGastosApplication.class, args);
	}

}
