package com.cg.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Online Shopping - v0.1
 * A Rest API based online Shopping system.
 * @author Naveen Prasanna V
 * Date: 21.09.2021
 */
@SpringBootApplication
@EnableSwagger2
public class OnlineShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

}
