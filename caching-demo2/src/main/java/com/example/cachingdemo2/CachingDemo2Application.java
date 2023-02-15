package com.example.cachingdemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CachingDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(CachingDemo2Application.class, args);
	}

}

/*

	using docker redis
	
		docker run --name my-redis -p 6379:6379 -d redis
	
		docker stop <id redis>
		docker start my-redis
 */