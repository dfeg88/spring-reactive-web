package com.danielfegan.reactiveweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ReactiveWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebApplication.class, args);
	}

}
