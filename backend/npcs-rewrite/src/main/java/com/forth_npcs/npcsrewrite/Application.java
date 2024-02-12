package com.forth_npcs.npcsrewrite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context= SpringApplication.run(Application.class, args);
		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		RestTemplate restTemplate=(RestTemplate)context.getBean("restTemplate", RestTemplate.class);
		//name : "restTemplate");
		System.out.println(restTemplate);
		//SpringApplication.run(Application.class, args);
	}

}
