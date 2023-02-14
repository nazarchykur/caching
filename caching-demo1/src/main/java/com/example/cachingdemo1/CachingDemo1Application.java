package com.example.cachingdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CachingDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(CachingDemo1Application.class, args);
	}

}
